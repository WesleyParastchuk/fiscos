package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.ProductClassification;

public interface ProductClassificationRepository extends JpaRepository<ProductClassification, Long> {

}
