package com.example.kalyapp.services;

import com.example.kalyapp.model.IngredientPrice;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientPriceService {
    AutoCrudOperation<IngredientPrice> ingredientPriceAutoCrudOperation;

    public IngredientPriceService(Connection connection) {
        this.ingredientPriceAutoCrudOperation = new AutoCrudOperation<>(new IngredientPrice(), connection);
    }

    public List<IngredientPrice> findAll() {
        return ingredientPriceAutoCrudOperation.findAll();
    }
    public IngredientPrice findById(int id) {
        return ingredientPriceAutoCrudOperation.findById(id);
    }
    public IngredientPrice save(IngredientPrice toSave) {
        return ingredientPriceAutoCrudOperation.save(toSave);
    }
    public IngredientPrice update(IngredientPrice toUpdate) {
        return ingredientPriceAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(int id) {
        return ingredientPriceAutoCrudOperation.deleteById(id);
    }

    public IngredientPrice currentIngredientPrice() {
        if (ingredientPriceAutoCrudOperation.findAll().isEmpty()) {
            return new IngredientPrice(
                    null,
                    null,
                    LocalDateTime.now(),
                    null
            );
        } else {
            return new LinkedList<>(ingredientPriceAutoCrudOperation.findAll()).getLast();
        }
    }
}
