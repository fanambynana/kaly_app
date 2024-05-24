package com.example.kalyapp.dto.resquest;

import com.example.kalyapp.model.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class MenuDtoRequest extends Menu {
    private int menuId;
    private List<Integer> ingredientIds;

    public MenuDtoRequest(int id, String name, int menuId, List<Integer> ingredientIds) {
        super(id, name);
        this.menuId = menuId;
        this.ingredientIds = ingredientIds;
    }
}
