package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.IngredientDtoResponse;
import com.example.kalyapp.dto.resquest.IngredientPriceDtoRequest;
import com.example.kalyapp.model.Ingredient;
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

    @Override
    public IngredientDtoResponse requestToResponse(Ingredient ingredientDtoRequest) {
        IngredientPriceDtoRequest currentIngredientPrice = ingredientPriceService.currentIngredientPrice();
        IngredientDtoResponse ingredientDtoResponse = new IngredientDtoResponse();

        ingredientDtoResponse.setId(ingredientDtoRequest.getId());
        ingredientDtoResponse.setName(ingredientDtoRequest.getName());
        ingredientDtoResponse.setUnity(ingredientDtoRequest.getUnity());
        ingredientDtoResponse.setPrice(currentIngredientPrice);

        return ingredientDtoResponse;
    }
}
