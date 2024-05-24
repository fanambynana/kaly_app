package com.example.kalyapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MenuApply extends Apply{
    private String menuId;

    public MenuApply(int id, int priceId, String menuId) {
        super(id, priceId);
        this.menuId = menuId;
    }
}
