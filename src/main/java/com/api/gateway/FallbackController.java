package com.api.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
	
	@GetMapping("/fallback")
	public Mono<String> fallback(){
		return  Mono.just("O sistema se encontra indisponível, tente mais tarde!!!");
	}
}
