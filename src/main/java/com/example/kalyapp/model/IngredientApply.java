package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientApply extends Apply {
    private int ingredientId;

    public IngredientApply(int id, int priceId, int ingredientId) {
        super(id, priceId);
        this.ingredientId = ingredientId;
    }
}
