package com.crealo.service;

import com.crealo.entity.Category;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

    Flux<Category> findAll();

    Mono<Category> findById(Integer id);

    Mono<Category> create(Category category);

    Mono<Category> update(Integer id, Category category);
}
