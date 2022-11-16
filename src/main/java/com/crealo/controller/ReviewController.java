package com.crealo.controller;

import com.crealo.dto.ProductReviewInput;
import com.crealo.entity.Appearance;
import com.crealo.entity.Product;
import com.crealo.entity.ProductReview;
import com.crealo.entity.User;
import com.crealo.service.ReviewService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Controller
public class ReviewController {

    private final ReviewService reviewService;

    @QueryMapping
    public Flux<ProductReview> reviews() {
        return reviewService.findAll();
    }

    @QueryMapping
    public Flux<ProductReview> productReviews(@Argument Integer productId) {
        return reviewService.findAllByProductId(productId);
    }

    @MutationMapping
    public Mono<ProductReview> createProductReview(@Argument Integer productId, @Argument ProductReviewInput productReview) {
        return Mono.just(ProductReview.builder()
                        .comment(productReview.getComment())
                        .rating(productReview.getRating())
                        .user(User.builder().id(UUID.fromString("3f766ce2-cf17-4c99-a591-b89aec525d4e")).build())
                        .product(Product.builder().id(productId).build())
                        .appearance(Appearance.builder().id(productReview.getAppearance().id()).build())
                        .build())
                .flatMap(reviewService::addProductReview)
                .map(ProductReview::getId)
                .flatMap(reviewService::findById).log();
    }
}
