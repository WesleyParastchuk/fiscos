package com.example.fiscos.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.product.ProductDTO;
import com.example.fiscos.model.ProductInvoice;

@Component
public class ProductMapper {

    private RawProductMapper rawProductMapper;
    private ProcessedProductMapper processedProductMapper;

    public ProductMapper(RawProductMapper rawProductMapper, ProcessedProductMapper processedProductMapper) {
        this.rawProductMapper = rawProductMapper;
        this.processedProductMapper = processedProductMapper;
    }

    public ProductDTO toDto(ProductInvoice productInvoice) {
        ProductDTO dto = new ProductDTO();
        dto.setId(productInvoice.getId());
        dto.setQuantity(productInvoice.getQuantity());
        dto.setUnitPrice(productInvoice.getUnitPrice());
        dto.setTotalPrice(productInvoice.getTotalPrice());

        dto.setRawProduct(this.rawProductMapper.toDto(productInvoice.getRawProduct()));
        dto.setClassifiedProduct(this.processedProductMapper.toDto(productInvoice.getProcessedProduct()));

        dto.setUnitOfMeasure(productInvoice.getUnitOfMeasure().getAbbreviation());



        return dto;
    }

    public List<ProductDTO> toDtoList(List<ProductInvoice> productInvoices) {
        return productInvoices.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
