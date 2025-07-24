package com.example.fiscos.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.nfeApi.RawProductNFeDTO;
import com.example.fiscos.dto.openAi.ProductClassifiedDTO;
import com.example.fiscos.mapper.RawProductMapper;
import com.example.fiscos.model.ProcessedProduct;
import com.example.fiscos.model.RawProduct;
import com.example.fiscos.repository.RawProductRepository;

@Service
public class RawProductService {

    private final RawProductRepository rawProductRepository;
    private final RawProductMapper rawProductMapper;

    public RawProductService(RawProductRepository rawProductRepository, RawProductMapper rawProductMapper) {
        this.rawProductRepository = rawProductRepository;
        this.rawProductMapper = rawProductMapper;
    }

    public RawProduct save(RawProductNFeDTO rawProductData) {
        return rawProductRepository.save(
                rawProductMapper.toEntity(rawProductData));
    }

    public List<RawProduct> saveAll(List<RawProduct> rawProducts, List<ProcessedProduct> processedProducts,
            List<ProductClassifiedDTO> classifiedProducts) {
        List<RawProduct> result = new ArrayList<>();
        for (ProductClassifiedDTO classifiedProduct : classifiedProducts) {
            RawProduct rawProduct = rawProducts.stream()
                .filter(rp -> rp.getName().equals(classifiedProduct.getProductName()))
                .findFirst()
                .orElse(null);

            ProcessedProduct processedProduct = processedProducts.stream()
                .filter(pp -> pp.getName().toUpperCase().equals(classifiedProduct.getResult().toUpperCase()))
                .findFirst()
                .orElse(null);

            if (rawProduct != null && processedProduct != null) {
                rawProduct.setProcessedProduct(processedProduct);
                result.add(rawProductRepository.save(rawProduct));
            }
        }
        return result;
    }

    public List<RawProduct> getNewRawProducts(List<RawProductNFeDTO> rawProductDtos) {
        List<RawProduct> newProducts = new ArrayList<>();
        for (RawProductNFeDTO dto : rawProductDtos) {
            RawProduct existing = rawProductRepository.findByNameAndCode(dto.getName(), dto.getCode());
            if (existing == null) {
                newProducts.add(rawProductMapper.toEntity(dto));
            }
        }
        return newProducts;
    }

    public List<RawProduct> getExistingRawProducts(List<RawProductNFeDTO> rawProductDtos) {
        List<RawProduct> existingProducts = new ArrayList<>();
        for (RawProductNFeDTO dto : rawProductDtos) {
            RawProduct existing = rawProductRepository.findByNameAndCode(dto.getName(), dto.getCode());
            if (existing != null) {
                existingProducts.add(existing);
            }
        }
        return existingProducts;
    }

}
