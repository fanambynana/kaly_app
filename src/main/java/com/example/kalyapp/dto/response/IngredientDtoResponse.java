package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Ingredient;
import com.example.kalyapp.model.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientDtoResponse extends Ingredient {
    private Price price;

    public IngredientDtoResponse(int id, String name, String unity, Price price) {
        super(id, name, unity);
        this.price = price;
    }
}
