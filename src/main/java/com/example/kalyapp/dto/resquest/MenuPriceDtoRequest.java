package com.example.kalyapp.dto.resquest;

import com.example.kalyapp.model.Price;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MenuPriceDtoRequest extends Price {
    private int menuId;

    public MenuPriceDtoRequest(int id, double amount, LocalDateTime changeDatetime, int menuId) {
        super(id, amount, changeDatetime);
        this.menuId = menuId;
    }
}
