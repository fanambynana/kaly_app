package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Resto;
import com.example.kalyapp.model.Sell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class SellDtoResponse extends Sell {
    private Resto resto;
    private MenuDtoResponse menu;

    public SellDtoResponse(int id, double qty, LocalDateTime datetime, MenuDtoResponse menu, Resto resto) {
        super(id, qty, datetime);
        this.menu = menu;
        this.resto = resto;
    }
}
