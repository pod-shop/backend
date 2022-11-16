package com.crealo.configuration;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.core.ReactiveTypeDescriptor;

import javax.annotation.PostConstruct;

@Deprecated
// Spring 5.3.10 adds SmallRye Mutiny support officially.
// see: https://github.com/spring-projects/spring-framework/pull/27331
//@Component
@Slf4j
@AllArgsConstructor
public class MutinyAdapter {

    private final ReactiveAdapterRegistry registry;

    @PostConstruct
    public void registerAdapters() {
        log.debug("registering MutinyAdapter");
        registry.registerReactiveType(
            ReactiveTypeDescriptor.singleOptionalValue(Uni.class, () -> Uni.createFrom().nothing()),
            uni -> ((Uni<?>) uni).convert().toPublisher(),
            publisher -> Uni.createFrom().publisher(publisher)
        );

        registry.registerReactiveType(
            ReactiveTypeDescriptor.multiValue(Multi.class, () -> Multi.createFrom().empty()),
            multi -> (Multi<?>) multi,
            publisher -> Multi.createFrom().publisher(publisher));
    }
}
