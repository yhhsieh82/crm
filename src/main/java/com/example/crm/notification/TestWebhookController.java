package com.example.crm.notification;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/test-webhook")
@RequiredArgsConstructor
public class TestWebhookController {
    private final GoogleChatWebhookWebClient googleChatWebhookWebClient;

    @ApiOperation(value = "test webhook")
    @PostMapping
    public void test(@RequestBody GoogleChatMessage message) throws JsonProcessingException {
        Mono<String> mono = googleChatWebhookWebClient.sendMsg(message);
        mono.subscribe();
    }
}