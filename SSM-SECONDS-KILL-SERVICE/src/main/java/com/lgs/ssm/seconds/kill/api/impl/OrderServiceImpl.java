package com.lgs.ssm.seconds.kill.api.impl;




import com.lgs.ssm.seconds.kill.api.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource(name = "DBOrderService")
    private com.lgs.ssm.seconds.kill.service.OrderService orderService;

    @Override
    public Integer createWrongOrder(int sid) {
        return orderService.createWrongOrder(sid);
    }

    @Override
    public Integer createOrderByOptimistic(int sid) {
        return orderService.createOrderByOptimistic(sid);
    }
}
