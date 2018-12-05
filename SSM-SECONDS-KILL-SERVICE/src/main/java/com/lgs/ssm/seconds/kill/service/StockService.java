package com.lgs.ssm.seconds.kill.service;

import com.lgs.ssm.seconds.kill.pojo.Stock;

public interface StockService {
    Stock getStockById(int id);

    int updateStockById(Stock stock);

    int updateStockByOptimistic(Stock stock);
}
