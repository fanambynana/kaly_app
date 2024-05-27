package com.example.kalyapp.services;

import com.example.kalyapp.model.MenuApply;
import com.example.kalyapp.repository.AutoCrudOperation;
import com.example.kalyapp.repository.KeyAndValue;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class MenuApplyService {
    AutoCrudOperation<MenuApply> menuApplyAutoCrudOperation;

    public MenuApplyService(Connection connection) {
        this.menuApplyAutoCrudOperation = new AutoCrudOperation<>(new MenuApply(), connection);
    }

    public List<MenuApply> findAll() {
        return menuApplyAutoCrudOperation.findAll();
    }
    public MenuApply findById(int id) {
        return menuApplyAutoCrudOperation.findById(id);
    }
    public MenuApply save(MenuApply toSave) {
        return menuApplyAutoCrudOperation.save(toSave);
    }
    public MenuApply update(MenuApply toUpdate) {
        return menuApplyAutoCrudOperation.update(toUpdate);
    }
    public Boolean deleteById(int id) {
        return menuApplyAutoCrudOperation.deleteById(id);
    }
    public List<MenuApply> findCustom(List<KeyAndValue> keyAndValueList) {
        return menuApplyAutoCrudOperation.findCustom(keyAndValueList);
    }
}
