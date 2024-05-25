package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Sell {
    private Integer id;
    private Double qty;
    private LocalDateTime datetime;
    private Integer restoId;
    private Integer menuId;
}
