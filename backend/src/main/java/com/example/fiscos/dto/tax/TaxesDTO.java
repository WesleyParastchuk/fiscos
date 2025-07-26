package com.example.fiscos.dto.tax;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxesDTO {
    
    @JsonProperty("federal")
    private TaxDTO federal;

    @JsonProperty("state")
    private TaxDTO state;

}
