package com.example.fiscos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.nfeApi.RawProductNFeDTO;
import com.example.fiscos.dto.rawProduct.RawProductDTO;
import com.example.fiscos.model.RawProduct;

@Component
public class RawProductMapper {

    public RawProduct toEntity(RawProductNFeDTO rawProductNFeDTO) {
        RawProduct rawProduct = new RawProduct();
        rawProduct.setName(rawProductNFeDTO.getName());
        rawProduct.setCode(rawProductNFeDTO.getCode());
        return rawProduct;
    }

    public RawProduct toEntity(RawProductDTO rawProductDto) {
        RawProduct rawProduct = new RawProduct();
        rawProduct.setId(rawProductDto.getId());
        rawProduct.setName(rawProductDto.getName());
        rawProduct.setCode(rawProductDto.getCode());
        return rawProduct;
    }

    public RawProductDTO toDto(RawProduct rawProduct) {
        RawProductDTO rawProductDTO = new RawProductDTO();
        rawProductDTO.setId(rawProduct.getId());
        rawProductDTO.setName(rawProduct.getName());
        rawProductDTO.setCode(rawProduct.getCode());
        return rawProductDTO;
    }

    public List<RawProduct> toEntityList(List<RawProductDTO> rawProductDTOs) {
        return rawProductDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public List<RawProduct> toEntityListFromNFe(List<RawProductNFeDTO> rawProductNFeDTOs) {
        return rawProductNFeDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
