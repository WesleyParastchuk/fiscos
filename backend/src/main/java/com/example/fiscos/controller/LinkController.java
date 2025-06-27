package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.dto.links.AddLinksDTO;
import com.example.fiscos.service.InvoiceService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Boolean> addLinks(@RequestBody @Valid AddLinksDTO dto) {
        return ResponseEntity.status(201).body(invoiceService.addLinks(dto));
    }

    @GetMapping
    public ResponseEntity<?> getMethodName() {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllInvoices() {
        invoiceService.deleteAllInvoices();
        return ResponseEntity.noContent().build();
    }
    

}
