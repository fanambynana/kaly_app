package com.example.kalyapp.dto.resquest;

import com.example.kalyapp.dto.response.IngredientDtoResponse;
import com.example.kalyapp.model.Enum.MVT_TYPE;
import com.example.kalyapp.model.Resto;
import com.example.kalyapp.model.StockMvt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class StockMvtDtoRequest extends StockMvt {
    public StockMvtDtoRequest(int id, MVT_TYPE mvtType, double mvtQty, LocalDateTime mvtDatetime, int restoId, int ingredientId) {
        super(id, mvtType, mvtQty, 0, mvtDatetime, restoId, ingredientId);
    }
}
