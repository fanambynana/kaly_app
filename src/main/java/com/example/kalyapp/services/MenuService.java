package com.example.kalyapp.services;

import com.example.kalyapp.dto.response.ComposeDtoResponse;
import com.example.kalyapp.dto.response.MenuDtoResponse;
import com.example.kalyapp.model.Menu;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class MenuService implements RequestToResponse<Menu, MenuDtoResponse> {
    AutoCrudOperation<Menu> menuAutoCrudOperation;
    MenuPriceService menuPriceService;
    IngredientService ingredientService;
    ComposeService composeService;

    public MenuService(Connection connection, MenuPriceService menuPriceService, IngredientService ingredientService, ComposeService composeService) {
        this.menuAutoCrudOperation = new AutoCrudOperation<>(new Menu(), connection);
        this.menuPriceService = menuPriceService;
        this.ingredientService = ingredientService;
        this.composeService = composeService;
    }

    public List<MenuDtoResponse> findAll() {
        return menuAutoCrudOperation.findAll().stream().map(this::requestToResponse).toList();
    }
    public MenuDtoResponse findById(int id) {
        return requestToResponse(menuAutoCrudOperation.findById(id));
    }
    public MenuDtoResponse save(Menu toSave) {
        if (menuAutoCrudOperation.save(toSave) == null) {
            throw new RuntimeException("Menu save failed");
        }
        return requestToResponse(toSave);
    }
    public MenuDtoResponse update(Menu toUpdate) {
        if (menuAutoCrudOperation.update(toUpdate) == null) {
            throw new RuntimeException("Menu update failed");
        }
        return requestToResponse(toUpdate);
    }
    public Boolean deleteById(int id) {
        return menuAutoCrudOperation.deleteById(id);
    }

    @Override
    public MenuDtoResponse requestToResponse(Menu menuDtoRequest) {
        if (menuDtoRequest == null) {
            return null;
        }

        MenuDtoResponse menuDtoResponse = new MenuDtoResponse();

        String menuIdKey = "menuId";
        int menuId = menuDtoRequest.getId();

        menuDtoResponse.setId(menuId);
        menuDtoResponse.setName(menuDtoRequest.getName());
        menuDtoResponse.setPrice(menuPriceService.currentMenuPrice());
        menuDtoResponse.setComposes(composeService.findCustom(List.of(
                new KeyAndValue(menuIdKey, String.valueOf(menuId))
        )));

        return menuDtoResponse;
    }
}
