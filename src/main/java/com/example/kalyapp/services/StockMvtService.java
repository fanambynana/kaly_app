package com.example.kalyapp.services;

import com.example.kalyapp.model.StockMvt;
import com.example.kalyapp.repository.AutoCrudOperation;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class StockMvtService {
    AutoCrudOperation<StockMvt> stockMvtAutoCrudOperation;
   public StockMvtService(Connection connection) {
        this.stockMvtAutoCrudOperation = new AutoCrudOperation<>(new StockMvt(), connection);
   }

   public StockMvt save(StockMvt toSave) {
       return stockMvtAutoCrudOperation.save(toSave);
   }
}
