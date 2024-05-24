package com.example.kalyapp.service;

import com.example.kalyapp.model.Compose;
import com.example.kalyapp.model.Ingredient;
import com.example.kalyapp.model.Menu;
import com.example.kalyapp.services.ComposeService;
import com.example.kalyapp.services.IngredientService;
import com.example.kalyapp.services.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenuServiceTest {
    MenuService menuService;
    ComposeService composeService;
    IngredientService ingredientService;

    @Autowired
    public void autowired(MenuService menuService, ComposeService composeService, IngredientService ingredientService) {
        this.menuService = menuService;
        this.composeService = composeService;
        this.ingredientService = ingredientService;
    }

    static int menuId = 1;
    static Ingredient ingredient = new Ingredient(1, "pain", "piece");
    static Ingredient ingredient2 = new Ingredient(2, "huile", "litre");

    static Compose compose = new Compose(1, 10, menuId, ingredient.getId());
    static Compose compose2 = new Compose(2, 1, menuId, ingredient2.getId());

    static Menu menuDtoRequest = new Menu(
            1,
            "pain+huile"
    );

    @Test
    void saveIngredientOk() {
        assertNotNull(ingredientService.save(ingredient));
        assertNotNull(ingredientService.save(ingredient2));
    }
    @Test
    void saveComposeOk() {
        assertNotNull(composeService.save(compose2));
        assertNotNull(composeService.save(compose));
    }
    @Test
    void saveMenuOk() {
        assertNotNull(menuService.save(menuDtoRequest));
    }
}
