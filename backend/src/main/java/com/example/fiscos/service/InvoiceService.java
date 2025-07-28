package com.example.fiscos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.external.nfeApi.CompleteNFeDTO;
import com.example.fiscos.dto.invoice.CompleteInvoiceDTO;
import com.example.fiscos.mapper.InvoiceMapper;
import com.example.fiscos.model.Invoice;
import com.example.fiscos.model.QRCode;
import com.example.fiscos.model.Supplier;
import com.example.fiscos.model.User;
import com.example.fiscos.repository.InvoiceRepository;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    public Invoice save(CompleteNFeDTO nfe, User user, Supplier supplier, QRCode qrCode) {
        Invoice invoice = invoiceMapper.toEntity(nfe);
        invoice.setUser(user);
        invoice.setSupplier(supplier);
        invoice.setQrCode(qrCode);

        return invoiceRepository.save(invoice);
    }

    public List<CompleteInvoiceDTO> getAllInvoices() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toCompleteDTO)
                .toList();
    }

    public CompleteInvoiceDTO getInvoiceById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toCompleteDTO)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }
}
