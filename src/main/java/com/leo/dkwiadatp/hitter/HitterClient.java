package com.leo.dkwiadatp.hitter;

import reactor.netty.http.client.HttpClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HitterClient {
    @Bean
    WebClient.Builder getHitterClient(){
    	HttpClient httpClient = HttpClient.create().followRedirect(true);
		return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient));
	}
}
