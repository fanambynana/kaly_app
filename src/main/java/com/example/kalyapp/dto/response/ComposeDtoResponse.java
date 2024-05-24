package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Compose;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class ComposeDtoResponse extends Compose {
    IngredientDtoResponse ingredient;

    public ComposeDtoResponse(int id, double necessaryQty, IngredientDtoResponse ingredient) {
        super(id, necessaryQty, 0,0);
        this.ingredient = ingredient;
    }
}
