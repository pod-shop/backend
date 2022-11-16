package com.crealo.repository;

import com.crealo.entity.Product;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@AllArgsConstructor
public class ProductRepository {

    private final Mutiny.SessionFactory sessionFactory;
    //private final Stage.SessionFactory sessionFactory;

    public Multi<Product> findAll() {
        var cb = this.sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(Product.class);
        var root = query.from(Product.class);

        return sessionFactory.withSession(session -> session.createQuery(query).getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }

    public Multi<Product> findByNameOrDescription(String q, int offset, int limit) {
        var cb = sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(Product.class);
        var root = query.from(Product.class);

        if (q != null && !q.trim().isEmpty()) {
            query.where(
                    cb.or(
//                            cb.like(root.get(Product_.name), "%" + q + "%"),
//                            cb.like(root.get(Product_.description), "%" + q + "%")
                    )
            );
        }

        return sessionFactory.withSession(session -> session.createQuery(query)
                        .setFirstResult(offset)
                        .setMaxResults(limit)
                        .getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }

    public Uni<Product> findById(Integer id) {
        Objects.requireNonNull(id, "id can not be null");

        return sessionFactory.withSession(session -> session.find(Product.class, id));
        //.onItem().ifNull().failWith(() -> new ProductNotFoundException(id));
    }

    public Uni<Product> save(Product product) {
        if (product.getId() == null) {
            return sessionFactory.withSession(session ->
                    session.persist(product)
                            .chain(session::flush)
                            .replaceWith(product)
            );
        } else {
            return sessionFactory.withSession(session ->
                    session.merge(product).onItem().call(session::flush)
            );
        }
    }

    public Multi<Product> saveAll(List<Product> data) {
        var array = data.toArray(new Product[0]);

        return sessionFactory.withSession(session -> {
                    session.persistAll(array);
                    session.flush();
                    return Uni.createFrom().item(array);
                })
                .onItem().transformToMulti(it -> Multi.createFrom().items(it));
    }

//    @Transactional
//    public Uni<Integer> updateStatus(Integer id, Product.Status status) {
//        var cb = sessionFactory.getCriteriaBuilder();
//        var delete = cb.createCriteriaUpdate(Product.class);
//        var root = delete.from(Product.class);
//
//        delete.set(root.get(Product_.status), status);
//        delete.where(cb.equal(root.get(Product_.id), id));

//        return this.session.createQuery(delete).executeUpdate();
//    }

    /*public Uni<Integer> deleteById(Integer id) {
        var cb = sessionFactory.getCriteriaBuilder();
        var delete = cb.createCriteriaDelete(Product.class);
        var root = delete.from(Product.class);

        delete.where(cb.equal(root.get(Product_.id), id));

        return this.sessionFactory.withTransaction((session, tx) ->
            session.createQuery(delete).executeUpdate()
        );
    }*/

    public Uni<Integer> deleteAll() {
        var cb = sessionFactory.getCriteriaBuilder();
        var delete = cb.createCriteriaDelete(Product.class);
        var root = delete.from(Product.class);

        return this.sessionFactory.withTransaction((session, tx) ->
                session.createQuery(delete).executeUpdate()
        );
    }

}
