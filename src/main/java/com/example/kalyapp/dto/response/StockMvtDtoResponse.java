package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Enum.MVT_TYPE;
import com.example.kalyapp.model.Resto;
import com.example.kalyapp.model.StockMvt;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class StockMvtDtoResponse extends StockMvt {
    private Resto resto;
    private IngredientDtoResponse ingredient;

    public StockMvtDtoResponse(int id, MVT_TYPE mvtType, double mvtQty, double updatedQty, LocalDateTime mvtDatetime, Resto resto, IngredientDtoResponse ingredient) {
        super(id, mvtType, mvtQty, updatedQty, mvtDatetime, 0, 0);
        this.resto = resto;
        this.ingredient = ingredient;
    }
}
