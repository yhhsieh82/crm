package com.example.crm.notification;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Service
public class GoogleChatWebhookWebClient {
    private static final String WEBHOOK_URL = "https://chat.googleapis.com/v1/spaces/AAAADmXsZTc/messages";
    private static final String FULL_WEBHOOK_URL = "https://chat.googleapis.com/v1/spaces/AAAADmXsZTc/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=C3Hfm4XNK0HVys5XMgjTSsSiUZZ_wPfiYpkFAn_PnYM%3D";

    private static final String PARAM_KEY = "AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI";
    private static final String PARAM_TOKEN = "C3Hfm4XNK0HVys5XMgjTSsSiUZZ_wPfiYpkFAn_PnYM%3D";

    private static final String TEST_REQUEST_BODY = "{\n    \"text\": {\n        \"email\": \"manager2@test.com\",\n        \"password\": \"password\",\n        \"role\": \"MANAGER\",\n        \"username\": \"MANAGER2\"\n    }\n}";
    private WebClient webClient;
    private ObjectMapper objectMapper;

    @Autowired
    public GoogleChatWebhookWebClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;

        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(WEBHOOK_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        this.webClient = WebClient.builder()
                .uriBuilderFactory(factory)
                .baseUrl(WEBHOOK_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create().wiretap(true)
                ))
                .build();
    }

    public Mono<String> sendMsg(GoogleChatMessage message) throws JsonProcessingException {

        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", PARAM_KEY)
                        .queryParam("token", PARAM_TOKEN)
                        .build())
                .body(BodyInserters.fromValue(objectMapper.writeValueAsString(message)))
                .exchangeToMono(response -> {
                    if (response.statusCode()
                            .equals(HttpStatus.OK)) {
                        return response.bodyToMono(String.class);
                    } else if (response.statusCode()
                            .is4xxClientError()) {
                        return Mono.just("Error response");
                    } else {
                        return response.createException()
                                .flatMap(Mono::error);
                    }
                });

    }
}
