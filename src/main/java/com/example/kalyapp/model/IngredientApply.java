package com.example.kalyapp.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientApply extends Apply {
    private int ingredientPriceId;

    public IngredientApply(int id, int priceId, int ingredientPriceId) {
        super(id, priceId);
        this.ingredientPriceId = ingredientPriceId;
    }
}
