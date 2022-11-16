package com.crealo.controller;

import com.crealo.dto.ProductInput;
import com.crealo.entity.Appearance;
import com.crealo.entity.Category;
import com.crealo.entity.Product;
import com.crealo.entity.Tag;
import com.crealo.entity.View;
import com.crealo.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.util.logging.Level;

@Slf4j
@AllArgsConstructor
@Controller
public class ProductController {
    //https://image.spreadshirtmedia.com/image-server/v1/productTypes/812/views/1/appearances/2,backgroundColor=f2f2f2,width=120,height=120,modelId=115,crop=detail.png
    private final ProductService productService;

    @QueryMapping
    public Flux<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public Mono<Product> product(@Argument Integer id) {
        return productService.findById(id);
    }

    @MutationMapping
    public Mono<Product> createProduct(@Argument ProductInput product) {
        return Mono.just(Product.builder()
                        .price(product.getPrice())
                        .name(product.getName())
                        .description(product.getDescription())
                        .enabled(product.getEnabled())
                        .category(Category.builder().id(product.getCategory().id()).build())
                        .tags(product.getTags().stream().map(t -> Tag.builder().id(t.id()).build()).toList())
                        .views(product.getViews().stream().map(v -> View.builder().id(v.id()).build()).toList())
                        .appearances(product.getAppearances().stream().map(a -> Appearance.builder().id(a.id()).build()).toList())
                        .build()
                )
                .flatMap(productService::create)
                .map(Product::getId)
                .flatMap(productService::findById);
    }

    @MutationMapping
    public Mono<Product> updateProduct(@Argument Integer id, @Argument ProductInput product) {
        return productService.findById(id)
                .map(p -> {
                    if (product.getPrice() != null && product.getPrice() > 0) {
                        p.setPrice(product.getPrice());
                    }
                    if (product.getName() != null) {
                        p.setName(product.getName());
                    }
                    if (product.getDescription() != null) {
                        p.setDescription(product.getDescription());
                    }
                    if (product.getEnabled() != null) {
                        p.setEnabled(product.getEnabled());
                    }
                    if (product.getCategory() != null && product.getCategory().id() != null) {
                        p.setCategory(Category.builder().id(product.getCategory().id()).build());
                    }
                    if (product.getTags() != null) {
                        p.setTags(product.getTags().stream().map(t -> Tag.builder().id(t.id()).build()).toList());
                    }
                    if (product.getViews() != null) {
                        p.setViews(product.getViews().stream().map(v -> View.builder().id(v.id()).build()).toList());
                    }
                    if (product.getAppearances() != null) {
                        p.setAppearances(product.getAppearances().stream().map(a -> Appearance.builder().id(a.id()).build()).toList());
                    }
                    return p;
                })
                .flatMap(p -> productService.update(id, p))
                .map(Product::getId)
                .flatMap(productService::findById);
    }

    @MutationMapping
    public Mono<Void> deleteProduct(@Argument Integer id) {
        throw new UnsupportedOperationException();
        //return this.posts.deleteById(id).map(d -> noContent().build());
    }

    private <T> Mono<T> processWithLog(Mono<T> monoToLog) {
        return monoToLog
                .log("ProductController.", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE);
    }

    private <T> Flux<T> processWithLog(Flux<T> fluxToLog) {
        return fluxToLog
                .log("ProductController.", Level.INFO, SignalType.ON_NEXT, SignalType.ON_COMPLETE);
    }
}
