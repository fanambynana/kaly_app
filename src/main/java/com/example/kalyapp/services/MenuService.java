package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.IngredientDtoResponse;
import com.example.kalyapp.dto.response.MenuDtoResponse;
import com.example.kalyapp.dto.resquest.MenuDtoRequest;
import com.example.kalyapp.dto.resquest.MenuPriceDtoRequest;
import com.example.kalyapp.model.Menu;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class MenuService implements RequestToResponse<MenuDtoRequest, MenuDtoResponse> {
    AutoCrudOperation<MenuDtoRequest> menuDtoRequestAutoCrudOperation;
    AutoCrudOperation<Menu> menuAutoCrudOperation;
    MenuPriceService menuPriceService;

    IngredientService ingredientService;

    public MenuService(Connection connection, MenuPriceService menuPriceService, IngredientService ingredientService) {
        this.menuDtoRequestAutoCrudOperation = new AutoCrudOperation<>(new MenuDtoRequest(), connection);
        this.menuAutoCrudOperation = new AutoCrudOperation<>(new Menu(), connection);
        this.menuPriceService = menuPriceService;
        this.ingredientService = ingredientService;
    }

    public List<MenuDtoResponse> findAll() {
        return menuDtoRequestAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
    }
    public MenuDtoResponse findById(int id) {
        return requestToResponse(menuDtoRequestAutoCrudOperation.findById(id));
    }
    public MenuDtoResponse save(MenuDtoRequest toSave) {
        List<Integer> ingredientIds = toSave.getIngredientIds();

        ingredientIds.forEach((id) -> {

        });
        return requestToResponse(menuDtoRequestAutoCrudOperation.save(toSave));
    }
    public MenuDtoResponse update(MenuDtoRequest toUpdate) {
        return requestToResponse(menuDtoRequestAutoCrudOperation.save(toUpdate));
    }
    public Boolean deleteById(int id) {
        return menuDtoRequestAutoCrudOperation.deleteById(id);
    }


    @Override
    public MenuDtoResponse requestToResponse(MenuDtoRequest menuDtoRequest) {
        MenuDtoResponse menuDtoResponse = new MenuDtoResponse();
        MenuPriceDtoRequest currentMenuPrice = menuPriceService.currentMenuPrice();
        List<IngredientDtoResponse> ingredientDtoResponses = menuDtoRequest.getIngredientIds().stream().map((e) -> ingredientService.findById(e)).toList();

        menuDtoResponse.setId(menuDtoRequest.getId());
        menuDtoResponse.setName(menuDtoRequest.getName());
        menuDtoResponse.setPrice(currentMenuPrice);
        menuDtoResponse.setIngredients(ingredientDtoResponses);

        return menuDtoResponse;
    }
}
