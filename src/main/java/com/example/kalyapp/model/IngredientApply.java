package com.example.kalyapp.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientApply extends Apply {
    private Integer ingredientPriceId;

    public IngredientApply(Integer id, Integer priceId, Integer ingredientPriceId) {
        super(id, priceId);
        this.ingredientPriceId = ingredientPriceId;
    }
}
