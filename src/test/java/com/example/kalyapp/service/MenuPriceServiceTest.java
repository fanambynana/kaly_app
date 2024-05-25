package com.example.kalyapp.service;

import com.example.kalyapp.model.MenuPrice;
import com.example.kalyapp.services.MenuPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MenuPriceServiceTest {
    @Autowired
    MenuPriceService menuPriceService;

    static MenuPrice menuPrice = new MenuPrice(
            1,
            5_000.0,
            LocalDateTime.now(),
            1
    );

    @Test
    void saveOk() {
        assertNotNull(menuPriceService.save(menuPrice));
    }
}
