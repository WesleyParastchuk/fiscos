package com.example.fiscos.dto.invoice;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("issueDate")
    private Long issueDate;

    @JsonProperty("totalValue")
    private BigDecimal totalValue;

}
