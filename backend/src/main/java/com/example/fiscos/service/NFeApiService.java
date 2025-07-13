package com.example.fiscos.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.fiscos.dto.nfeApi.CompleteNFeDTO;

@Service
public class NFeApiService {

    private final WebClient webClient;

    public NFeApiService(WebClient.Builder webClientBuilder, @Value("${external.nfe-url}") String nfeUrl) {
        this.webClient = webClientBuilder.baseUrl(nfeUrl).build();
    }

    public CompleteNFeDTO getNFeByUrl(String nfeUrl) {
        Map<String, String> requestBody = Map.of("url", nfeUrl);

        return webClient.post()
                .uri("/")
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(CompleteNFeDTO.class)
                .block();
    }

}
