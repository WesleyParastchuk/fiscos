package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
