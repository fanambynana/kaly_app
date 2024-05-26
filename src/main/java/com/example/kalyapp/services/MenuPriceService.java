package com.example.kalyapp.services;

import com.example.kalyapp.model.MenuPrice;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class MenuPriceService {
    AutoCrudOperation<MenuPrice> menuPriceAutoCrudOperation;

    public MenuPriceService(Connection connection) {
        this.menuPriceAutoCrudOperation = new AutoCrudOperation<>(new MenuPrice(), connection);
    }

    public List<MenuPrice> findAll() {
        return menuPriceAutoCrudOperation.findAll();
    }
    public MenuPrice findById(Integer id) {
        return menuPriceAutoCrudOperation.findById(id);
    }
    public MenuPrice save(MenuPrice toSave) {
        return menuPriceAutoCrudOperation.save(toSave);
    }
    public MenuPrice update(MenuPrice toUpdate) {
        return menuPriceAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(Integer id) {
        return menuPriceAutoCrudOperation.deleteById(id);
    }
    public List<MenuPrice> findCustom(List<KeyAndValue> keyAndValueList) {
        return menuPriceAutoCrudOperation.findCustom(keyAndValueList);
    }

    public MenuPrice currentMenuPrice() {
        List<MenuPrice> menuPrices = new LinkedList<>(menuPriceAutoCrudOperation.findAll());

        if (menuPrices.isEmpty()) {
            return new MenuPrice(
                    null,
                    0.0,
                    LocalDateTime.parse("2024-01-26T00:00:00"),
                    null
            );
        } else  {
            return menuPrices.getLast();
        }
    }
}
