package com.crealo.repository;

import com.crealo.entity.Category;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Slf4j
@Repository
@AllArgsConstructor
public class CategoryRepository {

    private final Mutiny.SessionFactory sessionFactory;

    public Multi<Category> findAll() {
        var cb = this.sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(Category.class);
        var root = query.from(Category.class);

        return sessionFactory.withSession(session -> session.createQuery(query).getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }

    public Uni<Category> findById(Integer id) {
        Objects.requireNonNull(id, "id can not be null");

        return this.sessionFactory.withSession(session -> session.find(Category.class, id));
    }

    public Uni<Category> save(Category category) {
        if (category.getId() == null) {
            return sessionFactory.withSession(session ->
                    session.persist(category)
                            .chain(session::flush)
                            .replaceWith(category)
            );
        } else {
            return sessionFactory.withSession(session ->
                    session.merge(category).onItem().call(session::flush)
            );
        }
    }
}
