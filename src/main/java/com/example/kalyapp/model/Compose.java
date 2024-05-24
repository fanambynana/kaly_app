package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Compose {
    private int id;
    private double necessaryQty;
    private int menuId;
    private int ingredientId;
}
