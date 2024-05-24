package com.example.kalyapp.repository;

import com.example.kalyapp.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class GenericModel {
    Compose compose = new Compose();
    Ingredient ingredient = new Ingredient();
    IngredientApply ingredientApply = new IngredientApply();
    IngredientPrice ingredientPrice = new IngredientPrice();
    Menu menu = new Menu();
    MenuApply menuApply = new MenuApply();
    MenuPrice menuPrice = new MenuPrice();
    Resto resto = new Resto();
    Sell sell = new Sell();
    StockMvt stockMvt = new StockMvt();
}
