package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.SellDtoResponse;
import com.example.kalyapp.model.Sell;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

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

    public List<SellDtoResponse> findAll() {
        return sellAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
    }
    public SellDtoResponse findById(Integer id) {
        return requestToResponse(sellAutoCrudOperation.findById(id));
    }
    public SellDtoResponse save(Sell toSave) {
        return requestToResponse(sellAutoCrudOperation.save(toSave));
    }
    public SellDtoResponse update(Sell toUpdate) {
        return requestToResponse(sellAutoCrudOperation.update(toUpdate));
    }
    public Boolean deleteById(Integer id) {
        return sellAutoCrudOperation.deleteById(id);
    }

    @Override
    public SellDtoResponse requestToResponse(Sell sellDtoRequest) {
        if (sellDtoRequest == null) {
            return null;
        }

        SellDtoResponse sellDtoResponse = new SellDtoResponse();

        sellDtoResponse.setId(sellDtoRequest.getId());
        sellDtoResponse.setQty(sellDtoRequest.getQty());
        sellDtoResponse.setDatetime(sellDtoRequest.getDatetime());
        sellDtoResponse.setRestoId(sellDtoRequest.getRestoId());
        sellDtoResponse.setMenuId(sellDtoRequest.getMenuId());
        sellDtoResponse.setResto(restoService.findById(sellDtoRequest.getRestoId()));
        sellDtoResponse.setMenu(menuService.findById(sellDtoRequest.getMenuId()));

        return sellDtoResponse;
    }
}
