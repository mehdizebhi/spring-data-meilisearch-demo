package dev.mehdizebhi.springdatameilisearchdemo.config;

import com.meilisearch.sdk.Client;
import com.meilisearch.sdk.Config;
import com.meilisearch.sdk.json.JacksonJsonHandler;
import com.meilisearch.sdk.json.JsonHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeiliClientConfig {

    @Value("${meilisearch.url}")
    private String hostUrl;

    @Value("${meilisearch.apiKey}")
    private String apiKey;

    @Bean
    public Client client(JsonHandler jsonHandler) {
        return new Client(new Config(hostUrl, apiKey, jsonHandler));
    }

    @Bean
    public JsonHandler jsonHandler() {
        return new JacksonJsonHandler();
    }
}
