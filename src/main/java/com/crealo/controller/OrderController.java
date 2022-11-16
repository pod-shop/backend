package com.crealo.controller;

import com.crealo.entity.Order;
import com.crealo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;

    @QueryMapping
    public Flux<Order> orders() {
        return orderService.findAll();
    }
}
