package com.lgs.ssm.seconds.kill.service.impl;

import com.lgs.ssm.seconds.kill.dao.StockMapper;
import com.lgs.ssm.seconds.kill.pojo.Stock;
import com.lgs.ssm.seconds.kill.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("DBStockService")
public class StockServiceImpl implements StockService{
    @Autowired
    private StockMapper stockMapper;

    public Stock getStockById(int id) {
        return stockMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateStockById(Stock stock) {
        return stockMapper.updateByPrimaryKeySelective(stock);
    }

    public int updateStockByOptimistic(Stock stock){ return stockMapper.updateByPrimaryKeyOptimistic(stock);}
}
