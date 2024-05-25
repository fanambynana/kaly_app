package com.example.kalyapp.dto.response;

import com.example.kalyapp.model.Compose;
import com.example.kalyapp.model.Menu;
import com.example.kalyapp.model.Price;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Data
public class MenuDtoResponse extends Menu {
    private List<ComposeDtoResponse> composes;
    private Price price;

    public MenuDtoResponse(Integer id, String name, List<ComposeDtoResponse> composes, Price price) {
        super(id, name);
        this.composes = composes;
        this.price = price;
    }
}
