package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.ProductInvoice;

public interface ProductInvoiceRepository extends JpaRepository<ProductInvoice, Long> {

}
