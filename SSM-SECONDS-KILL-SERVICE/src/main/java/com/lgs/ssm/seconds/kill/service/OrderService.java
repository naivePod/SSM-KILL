package com.lgs.ssm.seconds.kill.service;

public interface OrderService {
    Integer createWrongOrder(int sid);

    int createOrderByOptimistic(int sid);
}
