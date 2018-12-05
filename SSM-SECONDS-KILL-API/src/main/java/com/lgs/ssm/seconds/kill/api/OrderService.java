package com.lgs.ssm.seconds.kill.api;

public interface OrderService {

    Integer createWrongOrder(int sid);

    Integer createOrderByOptimistic(int sid);
}
