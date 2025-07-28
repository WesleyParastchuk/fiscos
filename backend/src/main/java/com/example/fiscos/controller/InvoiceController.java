package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.dto.invoice.CompleteInvoiceDTO;
import com.example.fiscos.service.InvoiceService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping()
    public List<CompleteInvoiceDTO> getAllInvoices() {
        return invoiceService.getAllInvoices();
    }

    @GetMapping("/{id}")
    public CompleteInvoiceDTO getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }
    
    
}
