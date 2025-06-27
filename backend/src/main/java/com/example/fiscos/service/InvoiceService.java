package com.example.fiscos.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fiscos.dto.links.AddLinksDTO;
import com.example.fiscos.model.Invoice;
import com.example.fiscos.repository.InvoiceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private ObjectMapper objectMapper;

    private final Path backupDir = Paths.get("backups");

    public Boolean addLinks(AddLinksDTO links) {
        try {
            List<String> linkList = links.getLinks();

            for (String link : linkList) {
                Invoice invoice = new Invoice();
                invoice.setLink(link);
                invoiceRepository.save(invoice);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(InvoiceService.class.getName()).log(Level.SEVERE, "Error saving links", e);
            return false;
        }

    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    @Transactional
    public void deleteAllInvoices() {
        List<Invoice> allInvoices = invoiceRepository.findAll();

        if (allInvoices.isEmpty()) {
            return;
        }

        try {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String filename = String.format("invoices-backup-%s.json", timestamp);
            Path backupFilePath = backupDir.resolve(filename);

            String jsonContent = objectMapper.writeValueAsString(allInvoices);

            Files.createDirectories(backupDir);
            Files.write(backupFilePath, jsonContent.getBytes(StandardCharsets.UTF_8));

            invoiceRepository.deleteAll();

        } catch (IOException e) {
            throw new RuntimeException("Falha ao criar backup, exclusão abortada.", e);
        }
    }

}
