package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.SellDtoResponse;
import com.example.kalyapp.model.Sell;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class SellService implements RequestToResponse<Sell, SellDtoResponse> {
    AutoCrudOperation<Sell> sellAutoCrudOperation;
    RestoService restoService;
    MenuService menuService;


    public SellService(Connection connection, RestoService restoService, MenuService menuService) {
        this.sellAutoCrudOperation = new AutoCrudOperation<>(new Sell(), connection);
        this.restoService = restoService;
        this.menuService = menuService;
    }



    @Override
    public SellDtoResponse requestToResponse(Sell sellDtoRequest) {
        SellDtoResponse sellDtoResponse = new SellDtoResponse();

        sellDtoResponse.setId(sellDtoRequest.getId());
        sellDtoResponse.setQty(sellDtoRequest.getQty());
        sellDtoResponse.setDatetime(sellDtoRequest.getDatetime());
        sellDtoResponse.setResto(restoService.findById(sellDtoRequest.getRestoId()));
        sellDtoResponse.setMenu(menuService.findById(sellDtoRequest.getMenuId()));

        return sellDtoResponse;
    }
}
