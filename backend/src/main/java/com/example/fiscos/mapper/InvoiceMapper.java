package com.example.fiscos.mapper;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.nfeApi.CompleteNFeDTO;
import com.example.fiscos.model.Invoice;

@Component
public class InvoiceMapper {

    public Invoice toEntity(CompleteNFeDTO nfe) {
        Invoice invoice = new Invoice();
        invoice.setIssueDate(nfe.getGeneralInfo().getEmissionDate());
        invoice.setTotalValue(nfe.getSummary().getTotalAmount());

        return invoice;
    }

}
