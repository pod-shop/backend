package com.crealo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@RestController
@SpringBootApplication
public class CrealoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrealoApplication.class, args);
    }

    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    @GetMapping
    public Mono<String> test() {
        return Mono.just("Server is alive!");
    }
}
