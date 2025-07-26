package com.example.fiscos.dto.tax;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxDTO {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("value")
    private BigDecimal value;

}
