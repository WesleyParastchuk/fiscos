package com.example.fiscos.dto.openAi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedProductClassificationDTO {

    @JsonProperty("resumo")
    private String productName;

    @JsonProperty("classificacaoId")
    private Long classificationId;
}
