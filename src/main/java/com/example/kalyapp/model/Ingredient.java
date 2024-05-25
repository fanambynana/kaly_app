package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
    private  Integer id;
    private String name;
    private String unity;
}
