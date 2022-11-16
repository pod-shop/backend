package com.crealo.service.impl;

import com.crealo.entity.Order;
import com.crealo.repository.OrderRepository;
import com.crealo.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public Flux<Order> findAll() {
        return Flux.from(repository.findAll());
    }
}
