package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.StockMvtDtoResponse;
import com.example.kalyapp.dto.resquest.StockMvtDtoRequest;
import com.example.kalyapp.model.StockMvt;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class StockMvtService implements RequestToResponse<StockMvt, StockMvtDtoResponse> {
    AutoCrudOperation<StockMvt> stockMvtAutoCrudOperation;
    RestoService restoService;
    IngredientService ingredientService;

   public StockMvtService(Connection connection, RestoService restoService, IngredientService ingredientService) {
        this.stockMvtAutoCrudOperation = new AutoCrudOperation<>(new StockMvt(), connection);
        this.restoService = restoService;
        this.ingredientService = ingredientService;
   }

   public List<StockMvtDtoResponse> findAll() {
       return stockMvtAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
   }
   public StockMvtDtoResponse findById(Integer id) {
       return requestToResponse(stockMvtAutoCrudOperation.findById(id));
   }
   public StockMvtDtoResponse save(StockMvtDtoRequest toSave) {
       return requestToResponse(stockMvtAutoCrudOperation.save(toSave));
   }
    public StockMvtDtoResponse update(StockMvtDtoRequest toUpdate) {
        return requestToResponse(stockMvtAutoCrudOperation.save(toUpdate));
    }
    public Boolean deleteById(Integer id) {
       return stockMvtAutoCrudOperation.deleteById(id);
    }

    @Override
    public StockMvtDtoResponse requestToResponse(StockMvt stockMvtDtoRequest) {
        if (stockMvtDtoRequest == null) {
            return null;
        }

        StockMvtDtoResponse stockMvtDtoResponse = new StockMvtDtoResponse();

        stockMvtDtoResponse.setId(stockMvtDtoRequest.getId());
        stockMvtDtoResponse.setMvtDatetime(stockMvtDtoRequest.getMvtDatetime());
        stockMvtDtoResponse.setMvtQty(stockMvtDtoRequest.getMvtQty());
        stockMvtDtoResponse.setUpdatedQty(stockMvtDtoRequest.getUpdatedQty());
        stockMvtDtoResponse.setMvtType(stockMvtDtoRequest.getMvtType());
        stockMvtDtoResponse.setResto(restoService.findById(stockMvtDtoRequest.getRestoId()));
        stockMvtDtoResponse.setIngredient(ingredientService.findById(stockMvtDtoRequest.getIngredientId()));

        return stockMvtDtoResponse;
    }
}
