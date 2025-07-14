package com.example.fiscos.dto.nfeApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDTO {

    @NotBlank(message = "Nome do produto não pode estar vazio.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Código do produto não pode estar vazio.")
    @JsonProperty("code")
    private String code;

    @Positive(message = "Quantidade deve ser positiva.")
    @JsonProperty("quantity")
    private double quantity;

    @NotBlank(message = "Unidade não pode estar vazia.")
    @JsonProperty("unit")
    private String unit;

    @PositiveOrZero(message = "Preço unitário deve ser zero ou positivo.")
    @JsonProperty("unitPrice")
    private double unitPrice;

    @PositiveOrZero(message = "Preço total deve ser zero ou positivo.")
    @JsonProperty("totalPrice")
    private double totalPrice;
}
