package com.example.fiscos.dto.processedProduct;

import java.util.List;

import com.example.fiscos.dto.classification.ClassificationDTO;
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

    @JsonProperty("classifications")
    private List<ClassificationDTO> classifications;

}
