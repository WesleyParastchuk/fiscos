package com.example.fiscos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.external.openAi.ProductClassifiedDTO;
import com.example.fiscos.dto.processedProduct.ProcessedProductDTO;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.mongo.ProcessedProductBackup;

@Component
public class ProcessedProductMapper {

    private ClassificationMapper classificationMapper;

    public ProcessedProductMapper(ClassificationMapper classificationMapper) {
        this.classificationMapper = classificationMapper;
    }

    public ProcessedProduct toEntity(ProcessedProductDTO processedProductDto) {

        ProcessedProduct processedProduct = new ProcessedProduct();
        processedProduct.setId(processedProductDto.getId());
        processedProduct.setName(processedProductDto.getName());

        return processedProduct;
    }

    public ProcessedProduct toEntity(ProcessedProductBackup processedProductBackup) {

        ProcessedProduct processedProduct = new ProcessedProduct();
        processedProduct.setName(processedProductBackup.getName());

        return processedProduct;
    }

    public List<ProcessedProduct> toEntityListFromBackup(List<ProcessedProductBackup> processedProductBackups) {
        return processedProductBackups.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public ProcessedProductDTO toDto(ProcessedProduct processedProduct) {

        ProcessedProductDTO processedProductDTO = new ProcessedProductDTO();
        processedProductDTO.setId(processedProduct.getId());
        processedProductDTO.setName(processedProduct.getName());
        processedProductDTO.setClassifications(processedProduct.getProductClassifications().stream()
                .map(classification -> classificationMapper.toDto(classification.getClassification()))
                .collect(Collectors.toList()));

        return processedProductDTO;
    }

    public ProcessedProductBackup toBackup(ProcessedProduct processedProduct) {

        ProcessedProductBackup processedProductBackup = new ProcessedProductBackup();
        processedProductBackup.setName(processedProduct.getName());

        return processedProductBackup;
    }

    public ProcessedProduct toEntity(ProductClassifiedDTO productClassifiedDTO) {
        ProcessedProduct processedProduct = new ProcessedProduct();
        processedProduct.setName(productClassifiedDTO.getResult());
        return processedProduct;
    }

}
