package com.leo.dkwiadatp.hitter;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/hitter")
public class Hitter {
	
	@Autowired
	private WebClient.Builder hitterClientBuilder;
	
	@GetMapping("/{webString}")
	public Mono<Object> hit(@PathVariable String webString ) {
		long start = System.currentTimeMillis();
			return hitterClientBuilder.build().get()
	                .uri(webString)
	                .exchangeToMono(response -> {
	                    long duration = System.currentTimeMillis() - start;
	                    int statusCode = response.statusCode().value();

	                    return response.bodyToMono(String.class)
	                            .defaultIfEmpty("")
	                            .map(body -> Map.of(
	                                    "url", webString,
	                                    "statusCode", statusCode,
	                                    "durationMs", duration,
	                                    "bodySnippet", body.substring(0, Math.min(200, body.length()))
	                            ));
	                });
//			return Optional.of(Long.toString(System.currentTimeMillis() - start) + " ms");
		}
	}
