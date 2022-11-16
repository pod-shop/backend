package com.crealo.controller;

import com.crealo.dto.CategoryInput;
import com.crealo.entity.Category;
import com.crealo.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;

    @QueryMapping
    public Flux<Category> categories() {
        return categoryService.findAll();
    }

    @QueryMapping
    public Mono<Category> category(@Argument Integer id) {
        return categoryService.findById(id);
    }

    @MutationMapping
    public Mono<Category> createCategory(@Argument CategoryInput category) {
        return Mono.just(Category.builder()
                        .name(category.getName())
                        .parent(Category.builder().id(category.getParent().id()).build())
                        .build()
                )
                .flatMap(categoryService::create)
                .map(Category::getId)
                .flatMap(categoryService::findById);
    }

    @MutationMapping
    public Mono<Category> updateCategory(@Argument Integer id, @Argument CategoryInput category) {
        return categoryService.findById(id)
                .map(c -> {
                    //if (category.getName() != null) {
                    //    c.setName(category.getName());
                    //}
                    c.setParent(Category.builder().id(category.getParent().id()).build());
                    return c;
                })
                .flatMap(c -> categoryService.update(id, c))
                .map(Category::getId)
                .flatMap(categoryService::findById);
    }

    @MutationMapping
    public Mono<Void> deleteCategory(@Argument Integer id) {
        throw new UnsupportedOperationException();
        //return this.posts.deleteById(id).map(d -> noContent().build());
    }
}
