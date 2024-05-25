package com.example.kalyapp.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.example.kalyapp.error.ErrorFormatter.formatError;
import static com.example.kalyapp.repository.CaseConverter.convertToSnakeCase;

@Getter
@AllArgsConstructor
public class AutoCrudOperation<T> implements CrudOperation<T> {
    private T model;
    private Connection connection;

    @Override
    public T save(T toSave) {
        PreparedStatement preparedStatement = null;

        Class<?> clazz = toSave.getClass();
        String className = clazz.getSimpleName();
        String classNameInSnakeCase = convertToSnakeCase(className);

        Field[] clazzFields = clazz.getDeclaredFields();
        List<Field> fields;

        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != Object.class) {
            List<Field> fieldList = new ArrayList<>();
            fieldList.addAll(Arrays.stream(clazzFields).toList());
            fieldList.addAll(Arrays.stream(superClazz.getDeclaredFields()).toList());

            fields = fieldList;
        } else {
            fields = Arrays.stream(clazzFields).toList();
        }

        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        List<Field> fieldList = new ArrayList<>();
        T returned = null;

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(toSave) == null ) {
                    continue;
                }
                if (!columns.isEmpty()) {
                    columns.append(", ");
                    values.append(", ");
                }
                columns.append(convertToSnakeCase(field.getName()));
                if (field.getType().isEnum()) {
                    values.append(String.format("'%s'", field.get(toSave)));
                } else {
                    values.append("?");
                    fieldList.add(field);
                }
            }
            String insertQuery = String.format(
                    "INSERT INTO %s (" + columns + ") VALUES (" + values + ")",
                    classNameInSnakeCase
            );
            preparedStatement = connection.prepareStatement(insertQuery);
            int parameterIndex = 1;
            for (Field field : fieldList) {
                if (field.getType() == LocalDateTime.class) {
                    preparedStatement.setTimestamp(parameterIndex++, Timestamp.valueOf((LocalDateTime) field.get(toSave)));
                } else {
                    preparedStatement.setObject(parameterIndex++, field.get(toSave));
                }
            }
            if (preparedStatement.executeUpdate() > 0) {
                returned = toSave;
            }
        } catch (Exception exception) {
            System.err.println(
                    formatError(exception, "while saving the " + className, toSave)
            );
        } finally {
            closeSession(preparedStatement, null);
        }
        return returned;
    }

    @Override
    public boolean deleteById(int id) {
        PreparedStatement preparedStatement = null;

        Class<?> clazz = getModel().getClass();
        String className = clazz.getSimpleName();
        String classNameInSnakeCase = convertToSnakeCase(className);
        boolean isDeleted = false;

        try {
            String query = String.format(
                    "DELETE FROM %s WHERE id_%s = %s",
                    classNameInSnakeCase,
                    classNameInSnakeCase,
                    id
            );
            preparedStatement = connection.prepareStatement(query);
            isDeleted = preparedStatement.executeUpdate() > 0;
        } catch (Exception exception) {
            System.err.println(
                    formatError(exception,
                    String.format("deleting the %s with id %s", className, id),
                null)
            );
        } finally {
            closeSession(preparedStatement, null);
        }
        return isDeleted;
    }

    @Override
    public T update(T toUpdate) {
        PreparedStatement preparedStatement = null;

        Class<?> clazz = toUpdate.getClass();
        String className = clazz.getSimpleName();
        String classNameInSnakeCase = convertToSnakeCase(className);

        Field[] fields = clazz.getDeclaredFields();
        StringBuilder dataUpdate = new StringBuilder();
        List<Field> fieldList = new ArrayList<>();
        T returned = null;

        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase("id")) {
                    continue;
                }
                if (field.get(toUpdate) != null) {
                    if (!dataUpdate.isEmpty()) {
                        dataUpdate.append(", ");
                    }
                    dataUpdate.append(
                            String.format(
                                    "%s = ?",
                                    convertToSnakeCase(field.getName())
                            )
                    );
                    fieldList.add(field);
                }
            }
            String query = String.format(
                    "UPDATE %s SET %s WHERE id = %s",
                    classNameInSnakeCase,
                    dataUpdate,
                    getModelId(toUpdate)
            );
            preparedStatement = connection.prepareStatement(query);
            int parameterIndex = 1;
            for (Field field : fieldList) {
                preparedStatement.setObject(parameterIndex++, field.get(toUpdate));
            }
            if (preparedStatement.executeUpdate() > 0) {
                returned = toUpdate;
            }
        } catch (Exception exception) {
            System.err.println(
                    formatError(exception, "updating the " + className, toUpdate)
            );
        } finally {
           closeSession(preparedStatement, null);
        }
        return returned;
    }

    @Override
    public List<T> findAll() {
        List<T> dataList = new LinkedList<>(find(null));
        if (dataList.getFirst() == null) {
            dataList.clear();
        }
        return dataList;
    }

    @Override
    public T findById(int id) {
        Class<?> clazz = getModel().getClass();
        List<T> list = new LinkedList<>(find(
            List.of(new KeyAndValue("id", String.valueOf(id)))
        ));
        if (list.isEmpty()) {
            return null;
        } else {
            return list.getFirst();
        }
    }

    @Override
    public List<T> findCustom(List<KeyAndValue> keyAndValueList) {
        return find(keyAndValueList);
    }

    private List<T> find(List<KeyAndValue> keyAndValueList) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Class<?> clazz = model.getClass();
        String className = clazz.getSimpleName();
        Field[] fields = clazz.getDeclaredFields();
        List<T> dataList = new ArrayList<>();
        dataList.add(null);

        try {
            String queryConstraint = "";
            String key;
            String value;
            int id;
            String classNameInSnakeCase = convertToSnakeCase(className);

            if (keyAndValueList != null) {
                for (KeyAndValue keyAndValue : keyAndValueList) {
                    key = keyAndValue.getKey();
                    value = keyAndValue.getValue();

                    if (key != null) {
                        if (keyAndValueList.indexOf(keyAndValue) == 0) {
                            queryConstraint = queryConstraint.concat(" WHERE ");
                        } else {
                            queryConstraint = queryConstraint.concat(" AND ");
                        }

                        key = convertToSnakeCase(key);
                        if (key.contains("id")) {
                            id = Integer.parseInt(value);
                            queryConstraint += String.format(
                                    " %s = %s ",
                                    key, id
                            );
                        } else {
                            queryConstraint += String.format(
                                    " %s = '%s' ",
                                    key, value);
                        }
                    }
                }
            }

            String query = "SELECT * FROM " + classNameInSnakeCase + queryConstraint;
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            T data;
            int i = 0;
            while (resultSet.next()) {
                if (i++ == 0) {
                    dataList.clear();
                }
                data = (T) getData(clazz);
                setFieldValue(resultSet, fields, data);
                dataList.add(data);
            }
        } catch (Exception exception) {
            System.err.println(
                    formatError(exception, "finding one or many" + className, null)
            );
        } finally {
            closeSession(preparedStatement, resultSet);
        }
        return dataList;
    }

    private Object getData(Class<?> clazz) throws IllegalAccessException {
        Object data = null;
        try {
            GenericModel genericModel = new GenericModel();
            Class<?> genericModelClass = genericModel.getClass();
            String classname = genericModelClass.getSimpleName();
            Field[] genericModelFields = genericModelClass.getDeclaredFields();

            for (Field genericModelField : genericModelFields) {
                genericModelField.setAccessible(true);
                if (genericModelField.getType() == clazz) {
                    data = genericModelField.get(genericModel);
                }
            }
            if (data == null) {
                throw  new RuntimeException(String.format("Classname %s not found in GenericModel", classname));
            }
        } catch (Exception exception) {
            System.err.println(formatError(exception, "getting data", null));
        }
        return data;
    }

    private void setFieldValue(ResultSet resultSet, Field[] fields, Object data) throws SQLException, IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldNameInSnakeCase = convertToSnakeCase(field.getName());
            if (field.getType().isEnum()) {
                String enumString = resultSet.getString(fieldNameInSnakeCase);
                if (enumString != null) {
                    field.set(data,
                            EnumConverter.convertStringToEnum((Class) field.getType(),
                                    enumString
                            )
                    );
                } else {
                    field.set(data, null);
                } 
            } else if(field.getType() == LocalDate.class) {
                field.set(data,
                        resultSet.getDate(fieldNameInSnakeCase).toLocalDate()
                );
            } else if (field.getType() == LocalDateTime.class) {
                field.set(data,
                        resultSet.getTimestamp(fieldNameInSnakeCase).toLocalDateTime()
                );
            } else if (field.getType() == Instant.class) {
                field.set(data,
                        resultSet.getTimestamp(fieldNameInSnakeCase).toInstant()
                );
            } else {
                field.set(data, resultSet.getObject(fieldNameInSnakeCase));
            }
        }
    }

    private void closeSession(PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            System.err.println(formatError(e, "closing session", null));
        }
    }

    private Integer getModelId(Object objectModel) {
        Integer id = null;
        try {
            Class<?> clazz = getModel().getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equalsIgnoreCase("id")) {
                    Object idObj = field.get(objectModel);
                    if (idObj == null) {
                        throw new RuntimeException("Id is null");
                    } else {
                        id = (Integer) idObj;
                    }
                }
            }
        } catch (Exception exception) {
            System.err.println(formatError(exception, "getting model Id", objectModel));
        }
        return id;
    }
}
