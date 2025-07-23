package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.ProcessedProduct;

public interface ProcessedProductRepository extends JpaRepository<ProcessedProduct, Long> {
    ProcessedProduct findByNameIgnoreCase(String name);
}
