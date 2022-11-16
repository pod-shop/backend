package com.crealo.repository;

import com.crealo.entity.Order;
import io.smallrye.mutiny.Multi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class OrderRepository {

    private final Mutiny.SessionFactory sessionFactory;

    public Multi<Order> findAll() {
        var cb = this.sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(Order.class);
        var root = query.from(Order.class);

        return sessionFactory.withSession(session -> session.createQuery(query).getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }
}
