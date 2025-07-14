package com.example.fiscos.mapper;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.nfeApi.SupplierNFeDTO;
import com.example.fiscos.dto.supplier.SupplierDTO;
import com.example.fiscos.model.Supplier;

@Component
public class SupplierMapper {

    private final AddressMapper addressMapper;

    public SupplierMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public SupplierDTO toDto(Supplier supplier) {
        SupplierDTO supplierDTO = new SupplierDTO();
        supplierDTO.setId(supplier.getId());
        supplierDTO.setName(supplier.getName());
        supplierDTO.setCnpj(supplier.getCnpj());
        if (supplier.getAddress() != null) {
            supplierDTO.setAddress(addressMapper.toDto(supplier.getAddress()));
        }
        return supplierDTO;
    }

    public Supplier toEntity(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierDTO.getId());
        supplier.setName(supplierDTO.getName());
        supplier.setCnpj(supplierDTO.getCnpj());
        if (supplierDTO.getAddress() != null) {
            supplier.setAddress(addressMapper.toEntity(supplierDTO.getAddress()));
        }
        return supplier;
    }

    public Supplier toEntity(SupplierNFeDTO supplierDTODto) {
        Supplier supplier = new Supplier();
        supplier.setName(supplierDTODto.getName());
        supplier.setCnpj(supplierDTODto.getCnpj());
        if (supplierDTODto.getAddress() != null) {
            supplier.setAddress(addressMapper.toEntity(supplierDTODto.getAddress()));
        }
        return supplier;
    }
}
