package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {

}
