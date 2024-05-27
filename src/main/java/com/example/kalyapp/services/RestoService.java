package com.example.kalyapp.services;

import com.example.kalyapp.model.Resto;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class RestoService {
    AutoCrudOperation<Resto> restoAutoCrudOperation;

    public RestoService(Connection connection) {
        this.restoAutoCrudOperation = new AutoCrudOperation<>(new Resto(), connection);
    }

    public List<Resto> findAll() {
        return restoAutoCrudOperation.findAll();
    }
    public Resto findById(int id) {
        return restoAutoCrudOperation.findById(id);
    }
    public Resto save(Resto toSave) {
        return restoAutoCrudOperation.save(toSave);
    }
    public Resto update(Resto toUpdate) {
        return restoAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(int id) {
        return restoAutoCrudOperation.deleteById(id);
    }
    public List<Resto> findCustom(List<KeyAndValue> keyAndValueList) {
        return restoAutoCrudOperation.findCustom(keyAndValueList);
    }
}
