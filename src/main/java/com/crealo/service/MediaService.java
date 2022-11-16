package com.crealo.service;

import com.crealo.entity.Media;
import reactor.core.publisher.Flux;

public interface MediaService {

    Flux<Media> findAll();
}
