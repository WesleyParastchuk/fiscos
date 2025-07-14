package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.RawProduct;

public interface RawProductRepository extends JpaRepository<RawProduct, Long> {

}
