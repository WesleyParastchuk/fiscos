package com.example.fiscos.service;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.processedProduct.ProcessedProductDTO;
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

    public ProcessedProduct save(ProcessedProductDTO processedProductDto) {
        ProcessedProduct processedProduct = processedProductMapper.toEntity(processedProductDto);
        return processedProductRepo.save(processedProduct);
    }

}
