package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Resto;
import com.example.kalyapp.model.Sell;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class SellDtoResponse extends Sell {
    private Resto resto;
    private MenuDtoResponse menu;

    public SellDtoResponse(Integer id, Double qty, LocalDateTime datetime, MenuDtoResponse menu, Resto resto) {
        super(id, qty, datetime, resto.getId(), menu.getId());
        this.menu = menu;
        this.resto = resto;
    }
}
