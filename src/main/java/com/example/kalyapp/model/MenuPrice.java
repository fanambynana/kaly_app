package com.example.kalyapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class MenuPrice extends Price {
    private int menuId;

    public MenuPrice(int id, double amount, LocalDateTime changeDatetime, int menuId) {
        super(id, amount, changeDatetime);
        this.menuId = menuId;
    }
}
