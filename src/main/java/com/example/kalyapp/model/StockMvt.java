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
    private int id;
    private MVT_TYPE mvtType;
    private double mvtQty;
    private double remainingQty;
    private LocalDateTime mvtDatetime;
    private int restoId;
    private int ingredientId;
}
