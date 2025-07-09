package com.example.fiscos.dto.nfeApi;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TaxDTO {

    @PositiveOrZero(message = "Valor total do imposto deve ser zero ou positivo.")
    private double totalTaxAmount;

    @PositiveOrZero(message = "Valor do imposto federal deve ser zero ou positivo.")
    private double federal;

    @PositiveOrZero(message = "Valor do imposto estadual deve ser zero ou positivo.")
    private double state;
}
