package com.example.kalyapp.model;

import com.example.kalyapp.model.Enum.MVT_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockMvt {
    private Integer id;
    private MVT_TYPE mvtType;
    private Double mvtQty;
    private Double updatedQty;
    private LocalDateTime mvtDatetime;
    private Integer restoId;
    private Integer ingredientId;
}
