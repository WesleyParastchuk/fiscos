package com.example.fiscos.dto.nfeApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SummaryNFeDTO {

    @PositiveOrZero(message = "Total de itens deve ser zero ou positivo.")
    @JsonProperty("totalItems")
    private int totalItems;

    @PositiveOrZero(message = "Valor total deve ser zero ou positivo.")
    @JsonProperty("totalAmount")
    private double totalAmount;

    @PositiveOrZero(message = "Valor pago deve ser zero ou positivo.")
    @JsonProperty("amountPaid")
    private double amountPaid;

    @NotBlank(message = "Método de pagamento não pode estar vazio.")
    @JsonProperty("paymentMethod")
    private String paymentMethod;
}
