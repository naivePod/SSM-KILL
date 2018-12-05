package com.lgs.ssm.seconds.kill.service.impl;

import com.lgs.ssm.seconds.kill.dao.StockOrderMapper;
import com.lgs.ssm.seconds.kill.pojo.Stock;
import com.lgs.ssm.seconds.kill.pojo.StockOrder;
import com.lgs.ssm.seconds.kill.service.OrderService;
import com.lgs.ssm.seconds.kill.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Transactional(rollbackFor = Exception.class)
@Service(value = "DBOrderService")
public class OrderServiceImpl implements OrderService {
    @Resource(name = "DBStockService")
    private StockService stockService;
    @Autowired
    private StockOrderMapper stockOrderMapper;
    @Override
    public Integer createWrongOrder(int sid) {
        //check stock
        Stock stock = checkStock(sid);
        //扣库存
        saleStock(stock);

        int id = createOrder(stock);

        return id;
    }

    private Stock checkStock(int sid) {
        Stock stock = stockService.getStockById(sid);

        if(stock.getCount().equals(stock.getSale())) {
            throw new RuntimeException("not enough stock");
        }
        return stock;
    }

    private int createOrder(Stock stock) {
        StockOrder order = new StockOrder();
        order.setSid(stock.getId());
        order.setName(stock.getName());
        order.setCreateTime(new Date());
        int id = stockOrderMapper.insertSelective(order);
        return id;
    }

    private int saleStock(Stock stock) {
        stock.setSale(stock.getSale()+1);
        return stockService.updateStockById(stock);
    }
    private int saleStockOptimistic(Stock stock) {
        int row = stockService.updateStockByOptimistic(stock);
        if(row == 0) {
            throw new RuntimeException("乐观锁，锁库存失败");
        }
        return row;
    }


    public int createOrderByOptimistic(int sid) {
        //check stock
        Stock stock = checkStock(sid);

        //锁库存
        saleStockOptimistic(stock);
        int id = createOrder(stock);

        return id;
    }
}
