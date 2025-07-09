package com.example.fiscos.dto.nfeApi;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductDTO {

    @NotBlank(message = "Nome do produto não pode estar vazio.")
    private String name;

    @NotBlank(message = "Código do produto não pode estar vazio.")
    private String code;

    @Positive(message = "Quantidade deve ser positiva.")
    private double quantity;

    @NotBlank(message = "Unidade não pode estar vazia.")
    private String unit;

    @PositiveOrZero(message = "Preço unitário deve ser zero ou positivo.")
    private double unitPrice;

    @PositiveOrZero(message = "Preço total deve ser zero ou positivo.")
    private double totalPrice;
}
