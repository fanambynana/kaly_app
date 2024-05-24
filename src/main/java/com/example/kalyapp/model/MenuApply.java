package com.example.kalyapp.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class MenuApply extends Apply{
    private String menuPriceId;

    public MenuApply(int id, int priceId, String menuPriceId) {
        super(id, priceId);
        this.menuPriceId = menuPriceId;
    }
}
