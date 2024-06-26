package com.example.kalyapp.services;

import com.example.kalyapp.model.IngredientApply;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class IngredientApplyService {
    AutoCrudOperation<IngredientApply> ingredientApplyAutoCrudOperation;

    public IngredientApplyService(Connection connection) {
        this.ingredientApplyAutoCrudOperation = new AutoCrudOperation<>(new IngredientApply(), connection);
    }

    public List<IngredientApply> findAll() {
        return ingredientApplyAutoCrudOperation.findAll();
    }
    public IngredientApply findById(int id) {
        return ingredientApplyAutoCrudOperation.findById(id);
    }
    public IngredientApply save(IngredientApply toSave) {
        return ingredientApplyAutoCrudOperation.save(toSave);
    }
    public IngredientApply update(IngredientApply toUpdate) {
        return ingredientApplyAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(int id) {
        return ingredientApplyAutoCrudOperation.deleteById(id);
    }
    public List<IngredientApply> findCustom(List<KeyAndValue> keyAndValueList) {
        return ingredientApplyAutoCrudOperation.findCustom(keyAndValueList);
    }
}
