package com.crealo.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Set;

@Slf4j
@AllArgsConstructor
@Controller
public class DashboardController {

    @QueryMapping
    public Mono<Statistic> statistic() {
        return Mono.empty();
    }

    public record Statistic(int productsCount, int ordersCount, float totalEarnings,
                            Set<MonthlyStatistics> sales, Set<MonthlyStatistics> visitors) {
    }

    public record MonthlyStatistics(String month, int count) {
    }
}
