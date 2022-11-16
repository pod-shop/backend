package com.crealo.service;

import com.crealo.entity.Order;
import reactor.core.publisher.Flux;

public interface OrderService {

    Flux<Order> findAll();

}
