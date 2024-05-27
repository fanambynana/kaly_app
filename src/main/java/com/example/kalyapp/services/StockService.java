package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.StockDtoResponse;
import com.example.kalyapp.model.Stock;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;

import java.sql.Connection;
import java.util.List;

public class StockService implements RequestToResponse<Stock, StockDtoResponse>{
    AutoCrudOperation<Stock> stockAutoCrudOperation;
    IngredientService ingredientService;

    public StockService(Connection connection, IngredientService ingredientService) {
        this.stockAutoCrudOperation = new AutoCrudOperation<>(new Stock(), connection);
        this.ingredientService = ingredientService;
    }

    public List<StockDtoResponse> findAll() {
        return stockAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
    }
    public StockDtoResponse findById(int id) {
        return requestToResponse(stockAutoCrudOperation.findById(id));
    }
    public StockDtoResponse save(StockDtoResponse toSave) {
        return requestToResponse(stockAutoCrudOperation.save(toSave));
    }
    public StockDtoResponse update(StockDtoResponse toUpdate) {
        return requestToResponse(stockAutoCrudOperation.update(toUpdate));
    }
    public Boolean deleteById(int id) {
        return stockAutoCrudOperation.deleteById(id);
    }
    public List<StockDtoResponse> findCustom(List<KeyAndValue> keyAndValueList) {
        return stockAutoCrudOperation.findCustom(keyAndValueList).stream().map(this::requestToResponse).toList();
    }

    @Override
    public StockDtoResponse requestToResponse(Stock stock) {
        int ingredientId = stock.getIngredientId();
        return new StockDtoResponse(
                stock.getId(),
                stock.getQty(),
                stock.getUpdateDatetime(),
                ingredientId,
                ingredientService.findById(ingredientId)
        );
    }
}
