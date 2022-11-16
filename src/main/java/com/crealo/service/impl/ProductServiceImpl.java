package com.crealo.service.impl;

import com.crealo.entity.Product;
import com.crealo.repository.ProductRepository;
import com.crealo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Flux<Product> findAll() {
        return Flux.from(repository.findAll());
        //return Mono.fromCompletionStage(repository.findAll().subscribeAsCompletionStage()).flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Product> findById(Integer id) {
        return Mono.from(repository.findById(id).convert().toPublisher());
        //return Mono.fromCompletionStage(repository.findById(id).subscribeAsCompletionStage());
    }

    @Override
    public Mono<Product> create(Product product) {
        return Mono.from(repository.save(product).convert().toPublisher());
        //return Mono.fromCompletionStage(repository.save(product).subscribeAsCompletionStage());
    }

    @Override
    public Mono<Product> update(Integer id, Product product) {
        product.setId(id);
        return Mono.from(repository.save(product).convert().toPublisher());
        //return Mono.fromCompletionStage(repository.save(product).subscribeAsCompletionStage());
    }
}
