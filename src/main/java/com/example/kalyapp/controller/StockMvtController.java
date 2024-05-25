package com.example.kalyapp.controller;

import com.example.kalyapp.dto.response.StockMvtDtoResponse;
import com.example.kalyapp.dto.resquest.StockMvtDtoRequest;
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
    public ResponseEntity<StockMvtDtoResponse> saveOrUpdate(@RequestBody StockMvtDtoRequest stockMvtDtoRequest) {
        if (stockMvtDtoRequest.getId() == null) {
            return ResponseEntity.of(
                    Optional.of(stockMvtService.save(stockMvtDtoRequest))
            );
        } else {
            return ResponseEntity.of(
                    Optional.of(stockMvtService.update(stockMvtDtoRequest))
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
