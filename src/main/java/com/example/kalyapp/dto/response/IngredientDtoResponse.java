package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Ingredient;
import com.example.kalyapp.model.Price;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientDtoResponse extends Ingredient {
    private Price price;

    public IngredientDtoResponse(int id, String name, String unity, Price price) {
        super(id, name, unity);
        this.price = price;
    }
}
