package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.ComposeDtoResponse;
import com.example.kalyapp.model.Compose;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class ComposeService implements RequestToResponse<Compose, ComposeDtoResponse> {
    AutoCrudOperation<Compose> composeAutoCrudOperation;
    IngredientService ingredientService;

    public ComposeService(Connection connection, IngredientService ingredientService) {
        this.composeAutoCrudOperation = new AutoCrudOperation<>(new Compose(), connection);
        this.ingredientService = ingredientService;
    }

    public List<ComposeDtoResponse> findAll() {
        return composeAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
    }
    public ComposeDtoResponse findById(int id) {
        return requestToResponse(composeAutoCrudOperation.findById(id));
    }
    public ComposeDtoResponse save(Compose toSave) {
        return requestToResponse(composeAutoCrudOperation.save(toSave));
    }
    public ComposeDtoResponse update(Compose toUpdate) {
        return requestToResponse(composeAutoCrudOperation.update(toUpdate));
    }
    public Boolean deleteById(int id) {
        return composeAutoCrudOperation.deleteById(id);
    }
    public List<ComposeDtoResponse> findCustom(List<KeyAndValue> keyAndValueList) {
        return composeAutoCrudOperation.findCustom(keyAndValueList).stream().map(this::requestToResponse).toList();
    }

    @Override
    public ComposeDtoResponse requestToResponse(Compose compose) {
        if (compose == null) {
            return null;
        }
        ComposeDtoResponse composeDtoResponse = new ComposeDtoResponse();

        composeDtoResponse.setId(compose.getId());
        composeDtoResponse.setNecessaryQty(compose.getNecessaryQty());
        composeDtoResponse.setIngredient(ingredientService.findById(compose.getIngredientId()));
        composeDtoResponse.setMenuId(compose.getMenuId());
        composeDtoResponse.setIngredientId(compose.getIngredientId());

        return composeDtoResponse;
    }
}
