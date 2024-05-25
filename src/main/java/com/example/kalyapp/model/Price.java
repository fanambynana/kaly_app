package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Price {
    private Integer id;
    private Double amount;
    private LocalDateTime changeDatetime;
}
