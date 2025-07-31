package com.example.fiscos.mapper;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.external.nfeApi.CompleteNFeDTO;
import com.example.fiscos.dto.invoice.CompleteInvoiceDTO;
import com.example.fiscos.model.Invoice;

@Component
public class InvoiceMapper {

    private final SupplierMapper supplierMapper;
    private final UserMapper userMapper;
    private final TaxMapper taxMapper;
    private final ProductMapper productMapper;
    private final QrCodeMapper qrCodeMapper;

    public InvoiceMapper(SupplierMapper supplierMapper, UserMapper userMapper, TaxMapper taxMapper,
            ProductMapper productMapper, QrCodeMapper qrCodeMapper) {
        this.supplierMapper = supplierMapper;
        this.userMapper = userMapper;
        this.taxMapper = taxMapper;
        this.productMapper = productMapper;
        this.qrCodeMapper = qrCodeMapper;
    }

    public Invoice toEntity(CompleteNFeDTO nfe) {
        Invoice invoice = new Invoice();
        invoice.setIssueDate(nfe.getGeneralInfo().getEmissionDate());
        invoice.setTotalValue(nfe.getSummary().getTotalAmount());

        return invoice;
    }

    public CompleteInvoiceDTO toCompleteDTO(Invoice invoice) {
        CompleteInvoiceDTO dto = new CompleteInvoiceDTO();
        dto.setId(invoice.getId());
        dto.setIssueDate(invoice.getIssueDate());
        dto.setTotalValue(invoice.getTotalValue());

        dto.setUser(userMapper.toDto(invoice.getUser()));
        dto.setSupplier(supplierMapper.toDto(invoice.getSupplier()));
        dto.setTaxes(taxMapper.toTaxesDto(invoice.getTaxes()));
        dto.setProducts(productMapper.toDtoList(invoice.getProductInvoices()));
        dto.setQrcode(qrCodeMapper.toDto(invoice.getQrCode()));
        return dto;
    }

}
