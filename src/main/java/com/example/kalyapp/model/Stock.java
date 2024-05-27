package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stock {
    private Integer id;
    private Double qty;
    private LocalDateTime updateDatetime;
    private Integer ingredientId;
}
