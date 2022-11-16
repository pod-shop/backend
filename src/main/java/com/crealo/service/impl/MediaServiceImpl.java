package com.crealo.service.impl;

import com.crealo.entity.Media;
import com.crealo.repository.MediaRepository;
import com.crealo.service.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@Service
public class MediaServiceImpl implements MediaService {

    private final MediaRepository repository;

    @Override
    public Flux<Media> findAll() {
        return Flux.from(repository.findAll());
    }
}
