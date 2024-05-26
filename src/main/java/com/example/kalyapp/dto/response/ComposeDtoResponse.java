package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Compose;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class ComposeDtoResponse extends Compose {
    IngredientDtoResponse ingredient;

    public ComposeDtoResponse(Integer id, Double necessaryQty, IngredientDtoResponse ingredient) {
        super(id, necessaryQty, null, ingredient.getId());
        this.ingredient = ingredient;
    }
}
