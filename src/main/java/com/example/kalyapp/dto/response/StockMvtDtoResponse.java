package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Enum.MVT_TYPE;
import com.example.kalyapp.model.Resto;
import com.example.kalyapp.model.StockMvt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class StockMvtDtoResponse extends StockMvt {
    private Resto resto;
    private IngredientDtoResponse ingredient;

    public StockMvtDtoResponse(int id, MVT_TYPE mvtType, double mvtQty, double remainingQty, LocalDateTime mvtDatetime, Resto resto, IngredientDtoResponse ingredient) {
        super(id, mvtType, mvtQty, remainingQty, mvtDatetime);
        this.resto = resto;
        this.ingredient = ingredient;
    }
}
