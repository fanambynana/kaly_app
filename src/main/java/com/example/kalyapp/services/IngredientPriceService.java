package com.example.kalyapp.services;

import com.example.kalyapp.dto.resquest.IngredientPriceDtoRequest;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

@Service
public class IngredientPriceService {
    AutoCrudOperation<IngredientPriceDtoRequest> ingredientPriceAutoCrudOperation;

    public IngredientPriceService(Connection connection) {
        this.ingredientPriceAutoCrudOperation = new AutoCrudOperation<>(new IngredientPriceDtoRequest(), connection);
    }

    public List<IngredientPriceDtoRequest> findAll() {
        return ingredientPriceAutoCrudOperation.findAll();
    }
    public IngredientPriceDtoRequest findById(int id) {
        return ingredientPriceAutoCrudOperation.findById(id);
    }
    public IngredientPriceDtoRequest save(IngredientPriceDtoRequest toSave) {
        return ingredientPriceAutoCrudOperation.save(toSave);
    }
    public IngredientPriceDtoRequest update(IngredientPriceDtoRequest toUpdate) {
        return ingredientPriceAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(int id) {
        return ingredientPriceAutoCrudOperation.deleteById(id);
    }

    public IngredientPriceDtoRequest currentIngredientPrice() {
        return new LinkedList<>(ingredientPriceAutoCrudOperation.findAll()).getLast();
    }
}
