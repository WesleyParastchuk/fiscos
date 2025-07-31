package com.example.fiscos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.external.openAi.ProductClassifiedDTO;
import com.example.fiscos.mapper.ProcessedProductMapper;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.repository.ProcessedProductRepository;

@Service
public class ProcessedProductService {

    private final ProcessedProductRepository processedProductRepo;
    private final ProcessedProductMapper processedProductMapper;

    public ProcessedProductService(ProcessedProductRepository processedProductRepository,
            ProcessedProductMapper processedProductMapper) {
        this.processedProductRepo = processedProductRepository;
        this.processedProductMapper = processedProductMapper;
    }

    public List<ProcessedProduct> saveAll(List<ProductClassifiedDTO> processedProductDtos) {
        return processedProductDtos.stream()
                .map(dto -> {
                    String nameUpper = dto.getResult().toUpperCase();
                    ProcessedProduct existing = processedProductRepo.findByNameIgnoreCase(nameUpper);
                    if (existing == null) {
                        ProcessedProduct newProduct = processedProductMapper.toEntity(dto);
                        newProduct.setName(nameUpper);
                        return processedProductRepo.save(newProduct);
                    }
                    return existing;
                })
                .collect(Collectors.toList());
    }

    // retornar que não estão no banco
    public List<ProcessedProduct> getNewProcessedProducts(List<ProductClassifiedDTO> processedProductDtos) {
        return processedProductDtos.stream()
                .map(dto -> {
                    String nameUpper = dto.getResult().toUpperCase();
                    ProcessedProduct existing = processedProductRepo.findByNameIgnoreCase(nameUpper);
                    if (existing == null) {
                        return processedProductMapper.toEntity(dto);
                    }
                    return null;
                })
                .filter(pp -> pp != null)
                .collect(Collectors.toList());
    }
    

}
