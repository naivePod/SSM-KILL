package com.lgs.web.controller;


import com.crossoverjie.distributed.annotation.SpringControllerLimit;
import com.lgs.ssm.seconds.kill.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/a")
public class StockController {

    @Autowired
    private OrderService orderService;

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @RequestMapping("/createWrongOrder/{sid}")
    @ResponseBody
    public String createWrongOrder(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createWrongOrder(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }

    @RequestMapping("/createOrderByOptimistic/{sid}")
    @ResponseBody
    public String createOrderByOptimistic(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createOrderByOptimistic(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }

    @RequestMapping("/createOrderByOptimisticRedis/{sid}")
    @SpringControllerLimit
    @ResponseBody
    public String createOrderByOptimisticRedis(@PathVariable int sid) {
        logger.info("sid=[{}]", sid);
        int id = 0;
        try {
            id = orderService.createOrderByOptimistic(sid);
        } catch (Exception e) {
            logger.error("Exception",e);
        }
        return String.valueOf(id);
    }

    @RequestMapping("/health")
    @ResponseBody
    public String health() {
        logger.info("heath");
        return "OK";
    }


}
