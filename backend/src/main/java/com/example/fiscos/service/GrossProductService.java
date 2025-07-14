package com.example.fiscos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.grossProduct.GrossProductDTO;
import com.example.fiscos.mapper.GrossProductMapper;
import com.example.fiscos.model.GrossProduct;
import com.example.fiscos.repository.GrossProductRepository;

@Service
public class GrossProductService {

    private final GrossProductRepository grossProductRepository;
    private final GrossProductMapper grossProductMapper;

    public GrossProductService(GrossProductRepository grossProductRepository, GrossProductMapper grossProductMapper) {
        this.grossProductRepository = grossProductRepository;
        this.grossProductMapper = grossProductMapper;
    }

    public GrossProduct save(GrossProductDTO grossProductDto) {
        return grossProductRepository.save(
                grossProductMapper.toEntity(grossProductDto));
    }

    public List<GrossProduct> saveAll(List<GrossProductDTO> grossProductDtos) {
        List<GrossProduct> grossProducts = grossProductMapper.toEntityList(grossProductDtos);
        return grossProductRepository.saveAll(grossProducts);
    }

}
