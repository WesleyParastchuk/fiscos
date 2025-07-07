package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.TaxType;

public interface TaxTypeRepository extends JpaRepository<TaxType, Long> {

}
