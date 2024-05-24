package com.example.kalyapp.dto.resquest;

import com.example.kalyapp.model.Price;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientPriceDtoRequest extends Price {
    private int ingredientId;

    public IngredientPriceDtoRequest(int id, double amount, LocalDateTime changeDatetime, int ingredientId) {
        super(id, amount, changeDatetime);
        this.ingredientId = ingredientId;
    }
}
