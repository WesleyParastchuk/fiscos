package com.example.fiscos.dto.processedProduct;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedProductDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

}
