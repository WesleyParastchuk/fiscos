package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Supplier findByCnpj(String cnpj);
}
