package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Compose {
    private Integer id;
    private Double necessaryQty;
    private Integer menuId;
    private Integer ingredientId;
}
