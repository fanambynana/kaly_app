package com.example.kalyapp.controller;

import com.example.kalyapp.dto.response.StockMvtDtoResponse;
import com.example.kalyapp.model.StockMvt;
import com.example.kalyapp.services.StockMvtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/stock-mvt")
@AllArgsConstructor
public class StockMvtController {
    StockMvtService stockMvtService;

    @PutMapping("")
    public ResponseEntity<StockMvt> saveOrUpdate(@RequestBody StockMvt stockMvt) {
        if (stockMvt.getId() == null) {
            return ResponseEntity.of(
                    Optional.of(stockMvtService.save(stockMvt))
            );
        } else {
            return ResponseEntity.of(
                    Optional.of(stockMvtService.update(stockMvt))
            );
        }
    }
    @GetMapping("")
    public ResponseEntity<List<StockMvtDtoResponse>> findAll() {
        return ResponseEntity.of(
                Optional.of(stockMvtService.findAll())
        );
    }
}
