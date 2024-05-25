package com.example.kalyapp.controller;

import com.example.kalyapp.dto.response.MenuDtoResponse;
import com.example.kalyapp.model.Menu;
import com.example.kalyapp.services.MenuService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/menus")
@AllArgsConstructor
public class MenuController {
    MenuService menuService;

    @GetMapping("")
    public ResponseEntity<List<MenuDtoResponse>> findAll() {
        return ResponseEntity.of(
                Optional.of(menuService.findAll())
        );
    }
    @PutMapping("")
    public ResponseEntity<MenuDtoResponse> saveOrUpdate(@RequestBody Menu menu) {
        if (menu.getId() == null) {
            return ResponseEntity.of(
                    Optional.of(menuService.save(menu))
            );
        } else {
            return ResponseEntity.of(
                    Optional.of(menuService.update(menu))
            );
        }

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        return ResponseEntity.of(
                Optional.of(menuService.deleteById(id))
        );
    }
}
