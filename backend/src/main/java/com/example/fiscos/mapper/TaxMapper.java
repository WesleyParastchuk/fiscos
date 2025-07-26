package com.example.fiscos.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.tax.TaxDTO;
import com.example.fiscos.dto.tax.TaxesDTO;
import com.example.fiscos.model.Tax;

@Component
public class TaxMapper {

    public TaxDTO toDto(Tax tax) {
        TaxDTO taxDTO = new TaxDTO();
        taxDTO.setId(tax.getId());
        taxDTO.setValue(tax.getValue());
        return taxDTO;
    }

    public TaxesDTO toTaxesDto(List<Tax> taxes) {
        TaxesDTO taxesDTO = new TaxesDTO();
        taxesDTO.setFederal(
                this.toDto(taxes.stream()
                        .filter(t -> "FEDERAL".equals(t.getTaxType().getAbbreviation()))
                        .findFirst()
                        .orElse(null)));
        taxesDTO.setState(
                this.toDto(taxes.stream()
                        .filter(t -> "ESTADUAL".equals(t.getTaxType().getAbbreviation()))
                        .findFirst()
                        .orElse(null)));
        return taxesDTO;
    }
}
