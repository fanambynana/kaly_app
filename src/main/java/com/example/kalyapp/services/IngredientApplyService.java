package com.example.kalyapp.services;

import com.example.kalyapp.model.IngredientApply;
import com.example.kalyapp.repository.AutoCrudOperation;
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
    public IngredientApply findById(Integer id) {
        return ingredientApplyAutoCrudOperation.findById(id);
    }
    public IngredientApply save(IngredientApply toSave) {
        return ingredientApplyAutoCrudOperation.save(toSave);
    }
    public IngredientApply update(IngredientApply toUpdate) {
        return ingredientApplyAutoCrudOperation.save(toUpdate);
    }
    public Boolean deleteById(Integer id) {
        return ingredientApplyAutoCrudOperation.deleteById(id);
    }
}
