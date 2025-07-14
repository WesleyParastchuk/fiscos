package com.example.fiscos.dto.nfeApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxNFeDTO {

    @PositiveOrZero(message = "Valor total do imposto deve ser zero ou positivo.")
    @JsonProperty("totalTaxAmount")
    private double totalTaxAmount;

    @PositiveOrZero(message = "Valor do imposto federal deve ser zero ou positivo.")
    @JsonProperty("federal")
    private double federal;

    @PositiveOrZero(message = "Valor do imposto estadual deve ser zero ou positivo.")
    @JsonProperty("state")
    private double state;
}
