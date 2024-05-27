package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Ingredient;
import com.example.kalyapp.model.Stock;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class StockDtoResponse extends Stock {
    private Ingredient ingredient;

    public StockDtoResponse(Integer id, Double qty, LocalDateTime updateDatetime, Integer ingredientId, Ingredient ingredient) {
        super(id, qty, updateDatetime, ingredientId);
        this.ingredient = ingredient;
    }
}
