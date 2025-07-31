package com.example.fiscos.dto.external.nfeApi;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawProductNFeDTO {

    @NotBlank(message = "Nome do produto não pode estar vazio.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "Código do produto não pode estar vazio.")
    @JsonProperty("code")
    private String code;

    @Positive(message = "Quantidade deve ser positiva.")
    @JsonProperty("quantity")
    private BigDecimal quantity;

    @NotBlank(message = "Unidade não pode estar vazia.")
    @JsonProperty("unit")
    private String unitOfMeasure;

    @PositiveOrZero(message = "Preço unitário deve ser zero ou positivo.")
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @PositiveOrZero(message = "Preço total deve ser zero ou positivo.")
    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;
}
