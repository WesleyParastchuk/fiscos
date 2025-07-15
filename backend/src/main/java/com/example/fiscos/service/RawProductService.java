package com.example.fiscos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.nfeApi.RawProductNFeDTO;
import com.example.fiscos.mapper.RawProductMapper;
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

    public List<RawProduct> saveAll(List<RawProductNFeDTO> rawProductDtos) {
        List<RawProduct> rawProducts = rawProductMapper.toEntityListFromNFe(rawProductDtos);
        return rawProductRepository.saveAll(rawProducts);
    }

}
