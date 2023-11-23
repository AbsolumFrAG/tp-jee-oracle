package com.jevendstout.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {
    @Value("${api.internal.url}")
    private String internalApiUrl;

    @Value("${api.external.url}")
    private String externalApiUrl;

    @Bean(name = "internalRestTemplate")
    public RestTemplate internalRestTemplate() {
        return new RestTemplateBuilder().rootUri(internalApiUrl).build();
    }

    @Bean(name = "externalRestTemplate")
    public RestTemplate externalRestTemplate() {
        return new RestTemplateBuilder().rootUri(externalApiUrl).build();
    }
}
