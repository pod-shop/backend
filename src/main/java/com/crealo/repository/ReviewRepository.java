package com.crealo.repository;

import com.crealo.entity.ProductReview;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@AllArgsConstructor
@Repository
public class ReviewRepository {

    private final Mutiny.SessionFactory sessionFactory;

    public Multi<ProductReview> findAll() {
        var cb = sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(ProductReview.class);
        var root = query.from(ProductReview.class);

        return sessionFactory.withSession(session -> session.createQuery(query)
                        //.setFirstResult(offset)
                        //.setMaxResults(limit)
                        .getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }

    public Multi<ProductReview> findAllByProductId(Integer productId) {
        var cb = sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(ProductReview.class);
        var root = query.from(ProductReview.class);

//        query.where(cb.equal(root.get(ProductReview_.product), productId));

        return sessionFactory.withSession(session -> session.createQuery(query)
                        //.setFirstResult(offset)
                        //.setMaxResults(limit)
                        .getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }

    public Uni<ProductReview> findById(Integer id) {
        Objects.requireNonNull(id, "id can not be null");

        return sessionFactory.withSession(session -> session.find(ProductReview.class, id));
    }

    public Uni<ProductReview> save(ProductReview productReview) {
        if (productReview.getId() == null) {
            return sessionFactory.withSession(session ->
                    session.persist(productReview)
                            .chain(session::flush)
                            .replaceWith(productReview)
            );
        } else {
            return sessionFactory.withSession(session ->
                    session.merge(productReview).onItem().call(session::flush)
            );
        }
    }
}
