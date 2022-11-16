package com.crealo.controller;

import com.crealo.entity.Media;
import com.crealo.service.MediaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@Slf4j
@AllArgsConstructor
@Controller
public class MediaController {

    private final MediaService mediaService;

    @QueryMapping
    public Flux<Media> medias() {
        return mediaService.findAll();
    }
}
