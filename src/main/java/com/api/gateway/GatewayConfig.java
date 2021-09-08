package com.api.gateway;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
	
	private static final String FORWARD_FALLBACK = "forward:/fallback";
	
	private static final String CEP_REGEX = "(\\/viacep\\/)(\\d+)";
	private static final String CEP_REPLACE = "/ws/$2/json";
	
	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return	routeLocatorBuilder
				.routes()
				.route(p -> predicateSpecsHttpbin(p))
				.route(p -> predicateSpecsViaCep(p))
				.route( p -> predicateSpecsFallback(p))
				.build();
	}

	
	private Buildable<Route> predicateSpecsHttpbin(PredicateSpec p) {
		return p.path("/ip")
		.filters(f -> f.circuitBreaker(configConsumer -> configConsumer.setName("ip").setFallbackUri(FORWARD_FALLBACK)))
		.uri("http://httpbin.org:80");
	}

	private Buildable<Route> predicateSpecsFallback(PredicateSpec p) {
		return p.path("/delayed")
				.filters(f -> f.circuitBreaker(configConsumer -> 
				configConsumer.setName("delayed").setFallbackUri(FORWARD_FALLBACK)))
				.uri("http://localhost:9999");
	}

	private Buildable<Route> predicateSpecsViaCep(PredicateSpec p) {
		return p.path("/viacep/{cep}")
		.filters(f -> f.circuitBreaker(configConsumer -> configConsumer.setName("viaCep").setFallbackUri(FORWARD_FALLBACK))
				.rewritePath(CEP_REGEX,CEP_REPLACE))
		.uri("https://viacep.com.br");
	}
	

}

