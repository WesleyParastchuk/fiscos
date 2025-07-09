package com.example.fiscos.dto.nfeApi;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SummaryDTO {

    @PositiveOrZero(message = "Total de itens deve ser zero ou positivo.")
    private int totalItems;

    @PositiveOrZero(message = "Valor total deve ser zero ou positivo.")
    private double totalAmount;

    @PositiveOrZero(message = "Valor pago deve ser zero ou positivo.")
    private double amountPaid;

    @NotBlank(message = "Método de pagamento não pode estar vazio.")
    private String paymentMethod;
}
