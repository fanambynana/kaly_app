package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.IngredientDtoResponse;
import com.example.kalyapp.model.Ingredient;
import com.example.kalyapp.model.IngredientPrice;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class IngredientService implements RequestToResponse<Ingredient, IngredientDtoResponse>{
    AutoCrudOperation<Ingredient> ingredientAutoCrudOperation;
    IngredientPriceService ingredientPriceService;

    public IngredientService(Connection connection, IngredientPriceService ingredientPriceService) {
        this.ingredientAutoCrudOperation = new AutoCrudOperation<>(new Ingredient(), connection);
        this.ingredientPriceService = ingredientPriceService;
    }

    public List<IngredientDtoResponse> findAll() {
        return ingredientAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
    }
    public IngredientDtoResponse findById(int id) {
        return requestToResponse(ingredientAutoCrudOperation.findById(id));
    }
    public IngredientDtoResponse save(Ingredient toSave) {
        return requestToResponse(ingredientAutoCrudOperation.save(toSave));
    }
    public IngredientDtoResponse update(Ingredient toUpdate) {
        return requestToResponse(ingredientAutoCrudOperation.save(toUpdate));
    }
    public Boolean deleteById(int id) {
        return ingredientPriceService.deleteById(id);
    }

    // 21:19

    @Override
    public IngredientDtoResponse requestToResponse(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientPrice currentIngredientPrice = ingredientPriceService.currentIngredientPrice();
        IngredientDtoResponse ingredientDtoResponse = new IngredientDtoResponse();

        ingredientDtoResponse.setId(ingredient.getId());
        ingredientDtoResponse.setName(ingredient.getName());
        ingredientDtoResponse.setUnity(ingredient.getUnity());
        ingredientDtoResponse.setPrice(currentIngredientPrice);

        return ingredientDtoResponse;
    }
}
