package com.example.fiscos.dto.openAi;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductClassifiedDTO {

    @JsonProperty("produto")
    private String productName;

    @JsonProperty("resumo")
    private String result;
}
