package com.att.tdp.bisbis10.controller;

import com.att.tdp.bisbis10.dto.OrderDto;
import com.att.tdp.bisbis10.entity.Order;
import com.att.tdp.bisbis10.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderDto orderDto) {
        Order savedOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok("orderId: \"" + savedOrder.getId() + "\"");
    }

}
