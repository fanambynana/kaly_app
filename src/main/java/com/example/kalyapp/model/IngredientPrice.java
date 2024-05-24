package com.example.kalyapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class IngredientPrice extends Price {
    private  int ingredientId;

    public IngredientPrice(int id, double amount, LocalDateTime changeDatetime, int ingredientId) {
        super(id, amount, changeDatetime);
        this.ingredientId = ingredientId;
    }
}