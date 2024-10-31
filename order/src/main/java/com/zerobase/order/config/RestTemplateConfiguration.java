package com.zerobase.order.config;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.core5.util.TimeValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate() {

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
            .setDefaultConnectionConfig(
                ConnectionConfig.custom()
                    .setSocketTimeout(5, TimeUnit.SECONDS)
                    .setConnectTimeout(3, TimeUnit.SECONDS)
                    .build()
            )
            .setMaxConnTotal(100)
            .setMaxConnPerRoute(10)
            .build();

        CloseableHttpClient httpClient = HttpClientBuilder.create()
            .setConnectionManager(connectionManager)
            .evictIdleConnections(TimeValue.of(10, TimeUnit.SECONDS))
            .build();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters()
            .addFirst(new StringHttpMessageConverter(StandardCharsets.UTF_8));

        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }

}
