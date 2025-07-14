package com.example.fiscos.service;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.nfeApi.SupplierNFeDTO;
import com.example.fiscos.exceptions.DataConflictException;
import com.example.fiscos.mapper.SupplierMapper;
import com.example.fiscos.model.Supplier;
import com.example.fiscos.repository.SupplierRepository;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public void save(SupplierNFeDTO supplier) {
        Supplier newSupplier = supplierMapper.toEntity(supplier);
        if (supplierRepository.existsByCnpj(newSupplier.getCnpj())) {
            throw new DataConflictException("Fornecedor com CNPJ " + newSupplier.getCnpj() + " já existe.");
        }
        supplierRepository.save(newSupplier);
    }
}
