package com.example.kalyapp.services;

import com.example.kalyapp.model.Menu;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class MenuService {
    AutoCrudOperation<Menu> menuAutoCrudOperation;

    public MenuService(Connection connection) {
        this.menuAutoCrudOperation = new AutoCrudOperation<>(new Menu(), connection);
    }

    public Menu saveOrUpdate(Menu menu) {
        if (menu.getId() == 0) {
            return menuAutoCrudOperation.save(menu);
        } else {
            return menuAutoCrudOperation.update(menu);
        }
    }
    public Boolean deleteById(int id) {
        return menuAutoCrudOperation.deleteById(id);
    }
}
