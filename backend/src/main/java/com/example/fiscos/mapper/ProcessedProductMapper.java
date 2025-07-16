package com.example.fiscos.mapper;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.processedProduct.ProcessedProductDTO;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.mongo.ProcessedProductBackup;

@Component
public class ProcessedProductMapper {

    public ProcessedProduct toEntity(ProcessedProductDTO processedProductDto) {

        ProcessedProduct processedProduct = new ProcessedProduct();
        processedProduct.setId(processedProductDto.getId());
        processedProduct.setName(processedProductDto.getName());

        return processedProduct;
    }

    public ProcessedProductDTO toDTO(ProcessedProduct processedProduct) {

        ProcessedProductDTO processedProductDTO = new ProcessedProductDTO();
        processedProductDTO.setId(processedProduct.getId());
        processedProductDTO.setName(processedProduct.getName());

        return processedProductDTO;
    }

    public ProcessedProductBackup toBackup(ProcessedProduct processedProduct) {

        ProcessedProductBackup processedProductBackup = new ProcessedProductBackup();
        processedProductBackup.setName(processedProduct.getName());

        return processedProductBackup;
    }

}
