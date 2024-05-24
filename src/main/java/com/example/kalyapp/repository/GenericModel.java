package com.example.kalyapp.repository;

import com.example.kalyapp.dto.resquest.IngredientPriceDtoRequest;
import com.example.kalyapp.dto.resquest.MenuPriceDtoRequest;
import com.example.kalyapp.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GenericModel {
    Compose compose = new Compose();
    Ingredient ingredient = new Ingredient();
    IngredientApply ingredientApply = new IngredientApply();
    IngredientPriceDtoRequest ingredientPriceDtoRequest = new IngredientPriceDtoRequest();
    Menu menu = new Menu();
    MenuApply menuApply = new MenuApply();
    MenuPriceDtoRequest menuPriceDtoRequest = new MenuPriceDtoRequest();
    Resto resto = new Resto();
    Sell sell = new Sell();
    StockMvt stockMvt = new StockMvt();
}
