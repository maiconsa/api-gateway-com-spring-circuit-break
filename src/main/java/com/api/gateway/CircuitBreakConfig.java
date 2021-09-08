package com.api.gateway;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;

@Configuration
public class CircuitBreakConfig {
	@Bean
	public ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory(CircuitBreakerRegistry circuitBreakerRegistry, TimeLimiterRegistry timeLimiterRegistry) {
		ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory = new ReactiveResilience4JCircuitBreakerFactory(circuitBreakerRegistry,timeLimiterRegistry);
		reactiveResilience4JCircuitBreakerFactory.configure(
				builder -> builder
						.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(300)).build())
						.circuitBreakerConfig(circuitBreakerRegistry.getDefaultConfig()),
				"app");
		return reactiveResilience4JCircuitBreakerFactory;
	}


	
}
