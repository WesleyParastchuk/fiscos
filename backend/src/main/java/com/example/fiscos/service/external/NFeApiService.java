package com.example.fiscos.service.external;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.fiscos.dto.external.nfeApi.CompleteNFeDTO;
import com.example.fiscos.exceptions.ExternalApiException;

@Service
public class NFeApiService {

    private final WebClient webClient;

    public NFeApiService(WebClient.Builder webClientBuilder, @Value("${external.nfe-url}") String nfeUrl) {
        this.webClient = webClientBuilder.baseUrl(nfeUrl).build();
    }

    private CompleteNFeDTO getNFeByLink(String nfeUrl) {
        Map<String, String> requestBody = Map.of("url", nfeUrl);

        try {
            return webClient.post()
                    .uri("/")
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(String.class)
                                    .defaultIfEmpty("Erro desconhecido na resposta do serviço externo.")
                                    .map(errorMessage -> new ExternalApiException(
                                            "Erro ao consultar NFe: " + errorMessage)))
                    .bodyToMono(CompleteNFeDTO.class)
                    .block();
        } catch (Exception ex) {
            throw new ExternalApiException("Falha ao conectar com o serviço externo de NFe.", ex);
        }
    }

    public List<CompleteNFeDTO> getNFeByLinks(List<String> links) {
        return links.stream()
                .map(this::getNFeByLink)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
