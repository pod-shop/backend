package com.crealo.service.impl;

import com.crealo.entity.ProductReview;
import com.crealo.repository.ReviewRepository;
import com.crealo.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    @Override
    public Flux<ProductReview> findAll() {
        return Flux.from(repository.findAll());
    }

    @Override
    public Mono<ProductReview> findById(Integer id) {
        Mono.from(repository.findById(id).convert().toPublisher());
        return Mono.from(repository.findById(id).convert().toPublisher());
    }

    @Override
    public Flux<ProductReview> findAllByProductId(Integer productId) {
        return Flux.from(repository.findAllByProductId(productId));
    }

    @Override
    public Mono<ProductReview> addProductReview(ProductReview productReview) {
        return Mono.from(repository.save(productReview).convert().toPublisher());
    }
}
