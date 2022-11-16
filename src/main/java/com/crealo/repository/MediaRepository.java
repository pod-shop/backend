package com.crealo.repository;

import com.crealo.entity.Media;
import io.smallrye.mutiny.Multi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.stereotype.Repository;

@Slf4j
@AllArgsConstructor
@Repository
public class MediaRepository {

    private final Mutiny.SessionFactory sessionFactory;

    public Multi<Media> findAll() {
        var cb = sessionFactory.getCriteriaBuilder();
        var query = cb.createQuery(Media.class);
        var root = query.from(Media.class);

        return sessionFactory.withSession(session -> session.createQuery(query).getResultList())
                .onItem().transformToMulti(it -> Multi.createFrom().items(it.stream()));
    }
}
