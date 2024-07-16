package com.example.iismicroservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000); // Установите тайм-аут подключения (в миллисекундах)
        factory.setReadTimeout(100000); // Установите тайм-аут чтения (в миллисекундах)
        return new RestTemplate(factory);
    }
}
