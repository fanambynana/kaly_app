package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Menu;
import com.example.kalyapp.model.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MenuDtoResponse extends Menu {
    private List<IngredientDtoResponse> ingredients;
    private Price price;

    public MenuDtoResponse(int id, String name, List<IngredientDtoResponse> ingredients, Price price) {
        super(id, name);
        this.ingredients = ingredients;
        this.price = price;
    }
}
