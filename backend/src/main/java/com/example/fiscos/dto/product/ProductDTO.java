package com.example.fiscos.dto.product;

import java.math.BigDecimal;

import com.example.fiscos.dto.processedProduct.ProcessedProductDTO;
import com.example.fiscos.dto.rawProduct.RawProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("rawProduct")
    private RawProductDTO rawProduct;

    @JsonProperty("classifiedProduct")
    private ProcessedProductDTO classifiedProduct;

    @JsonProperty("quantity")
    private BigDecimal quantity;
    
    @JsonProperty("unitPrice")
    private BigDecimal unitPrice;

    @JsonProperty("totalPrice")
    private BigDecimal totalPrice;

    @JsonProperty("unitOfMeasure")
    private String unitOfMeasure;

}
