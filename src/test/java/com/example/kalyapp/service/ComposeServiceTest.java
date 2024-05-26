package com.example.kalyapp.service;

import com.example.kalyapp.dto.response.MenuDtoResponse;
import com.example.kalyapp.model.Compose;
import com.example.kalyapp.services.ComposeService;
import com.example.kalyapp.services.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ComposeServiceTest {
    ComposeService composeService;
    MenuService menuService;

    @Autowired
    public void autowired(ComposeService composeService, MenuService menuService) {
        this.composeService = composeService;
        this.menuService = menuService;
    }

    static int id = 4;
    static Compose compose = new Compose(
           id,
           10.0,
           1,
           1
    );

    @Test
    void saveOk() {
       assertNotNull(composeService.save(compose));
    }
    @Test
    void updateOk() {
        compose.setNecessaryQty(5.0);
        assertNotNull(composeService.update(compose));
    }
    @Test
    void deleteOk() {
        assertTrue(composeService.deleteById(id));
    }
    @Test
    void composeAssociatedOk() {
        assertTrue(menuService.findById(compose.getMenuId()).getComposes().contains(composeService.requestToResponse(compose)));
    }
}
