package com.example.kalyapp.service;

import com.example.kalyapp.model.Ingredient;
import com.example.kalyapp.services.IngredientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IngredientServiceTest {
    IngredientService ingredientService;

    @Autowired
    public void autowired(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    static int id = 4;
    static Ingredient ingredient = new Ingredient(
            id,
            "soja",
            "kg"
    );

    @Test
    void saveOk() {
        assertNotNull(ingredientService.save(ingredient));
    }
    @Test
    void deleteOk() {
        assertTrue(ingredientService.deleteById(id));
    }
    @Test
    void updateOk() {
        ingredient.setName("soja-edit");
        assertNotNull(ingredientService.update(ingredient));
    }
}
