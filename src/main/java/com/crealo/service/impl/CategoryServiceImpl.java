package com.crealo.service.impl;

import com.crealo.entity.Category;
import com.crealo.repository.CategoryRepository;
import com.crealo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public Flux<Category> findAll() {
        return Flux.from(repository.findAll());
    }

    @Override
    public Mono<Category> findById(Integer id) {
        return Mono.from(repository.findById(id).convert().toPublisher());
    }

    @Override
    public Mono<Category> create(Category category) {
        return Mono.from(repository.save(category).convert().toPublisher());
    }

    @Override
    public Mono<Category> update(Integer id, Category category) {
        category.setId(id);
        return Mono.from(repository.save(category).convert().toPublisher());
    }
}
