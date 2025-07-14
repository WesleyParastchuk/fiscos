package com.example.fiscos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.grossProduct.GrossProductDTO;
import com.example.fiscos.model.GrossProduct;

@Component
public class GrossProductMapper {

    public GrossProduct toEntity(GrossProductDTO grossProductDTO) {
        GrossProduct grossProduct = new GrossProduct();
        grossProduct.setId(grossProductDTO.getId());
        grossProduct.setName(grossProductDTO.getName());
        grossProduct.setCode(grossProductDTO.getCode());
        return grossProduct;
    }

    public GrossProductDTO toDTO(GrossProduct grossProduct) {
        GrossProductDTO grossProductDTO = new GrossProductDTO();
        grossProductDTO.setId(grossProduct.getId());
        grossProductDTO.setName(grossProduct.getName());
        grossProductDTO.setCode(grossProduct.getCode());
        return grossProductDTO;
    }

    public List<GrossProduct> toEntityList(List<GrossProductDTO> grossProductDTOs) {
        return grossProductDTOs.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
