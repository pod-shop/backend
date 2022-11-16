package com.crealo.service;

import com.crealo.entity.ProductReview;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {

    Flux<ProductReview> findAll();

    Mono<ProductReview> findById(Integer id);

    Flux<ProductReview> findAllByProductId(Integer productId);

    Mono<ProductReview> addProductReview(ProductReview productReview);

}
