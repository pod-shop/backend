package com.crealo.service;

import com.crealo.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> findAll();

    Mono<Product> findById(Integer id);

    Mono<Product> create(Product product);

    Mono<Product> update(Integer id, Product product);
}
