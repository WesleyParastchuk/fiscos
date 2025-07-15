package com.example.fiscos.service;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.nfeApi.CompleteNFeDTO;
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
}
