package com.example.kalyapp.services;

import com.example.kalyapp.dto.resquest.MenuPriceDtoRequest;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.List;

@Service
public class MenuPriceService {
    AutoCrudOperation<MenuPriceDtoRequest> menuPriceAutoCrudOperation;

    public MenuPriceService(Connection connection) {
        this.menuPriceAutoCrudOperation = new AutoCrudOperation<>(new MenuPriceDtoRequest(), connection);
    }

    public List<MenuPriceDtoRequest> findAll() {
        return menuPriceAutoCrudOperation.findAll();
    }
    public MenuPriceDtoRequest findById(int id) {
        return menuPriceAutoCrudOperation.findById(id);
    }
    public MenuPriceDtoRequest save(MenuPriceDtoRequest toSave) {
        return menuPriceAutoCrudOperation.save(toSave);
    }
    public MenuPriceDtoRequest update(MenuPriceDtoRequest toUpdate) {
        return menuPriceAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(int id) {
        return menuPriceAutoCrudOperation.deleteById(id);
    }

    public MenuPriceDtoRequest currentMenuPrice() {
        return new LinkedList<>(menuPriceAutoCrudOperation.findAll()).getLast();
    }
}
