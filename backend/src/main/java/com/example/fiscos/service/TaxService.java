package com.example.fiscos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.external.nfeApi.TaxNFeDTO;
import com.example.fiscos.model.Invoice;
import com.example.fiscos.model.Tax;
import com.example.fiscos.repository.TaxRepository;
import com.example.fiscos.repository.TaxTypeRepository;

@Service
public class TaxService {

    private final TaxRepository taxRepository;
    private final TaxTypeRepository taxTypeRepository;

    public TaxService(TaxRepository taxRepository, TaxTypeRepository taxTypeRepository) {
        this.taxRepository = taxRepository;
        this.taxTypeRepository = taxTypeRepository;
    }

    public List<Tax> save(TaxNFeDTO taxDto, Invoice invoice) {
        Tax federalTax = new Tax();
        federalTax.setTaxType(taxTypeRepository.findByAbbreviation("FEDERAL"));
        federalTax.setValue(taxDto.getFederal());
        federalTax.setInvoice(invoice);

        Tax stateTax = new Tax();
        stateTax.setTaxType(taxTypeRepository.findByAbbreviation("ESTADUAL"));
        stateTax.setValue(taxDto.getState());
        stateTax.setInvoice(invoice);

        Tax totalTax = new Tax();
        totalTax.setTaxType(taxTypeRepository.findByAbbreviation("TOTAL"));
        totalTax.setValue(taxDto.getTotalTaxAmount());
        totalTax.setInvoice(invoice);

        List<Tax> taxes = List.of(federalTax, stateTax, totalTax);

        return taxRepository.saveAll(taxes);
    }

}
