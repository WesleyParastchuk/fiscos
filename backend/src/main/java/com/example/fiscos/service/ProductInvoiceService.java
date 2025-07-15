package com.example.fiscos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.dto.nfeApi.CompleteNFeDTO;
import com.example.fiscos.model.Invoice;
import com.example.fiscos.model.ProductInvoice;
import com.example.fiscos.model.RawProduct;
import com.example.fiscos.repository.ProductInvoiceRepository;
import com.example.fiscos.repository.UnitOfMeasureRepository;

@Service
public class ProductInvoiceService {

    private final ProductInvoiceRepository productInvoiceRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public ProductInvoiceService(ProductInvoiceRepository productInvoiceRepository,
            UnitOfMeasureRepository unitOfMeasureRepository) {
        this.productInvoiceRepository = productInvoiceRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    public List<ProductInvoice> saveAll(CompleteNFeDTO nfe, Invoice invoice, List<RawProduct> rawProducts) {
        return rawProducts.stream()
                .map(rawProduct -> {
                    ProductInvoice productInvoice = new ProductInvoice();
                    nfe.getProducts().stream()
                            .filter(product -> product.getName().equals(rawProduct.getName())
                                    && product.getCode().equals(rawProduct.getCode()))
                            .findFirst()
                            .ifPresent(product -> {
                                productInvoice.setQuantity(product.getQuantity());
                                productInvoice.setUnitPrice(product.getUnitPrice());
                                productInvoice.setTotalPrice(product.getTotalPrice());
                                productInvoice.setUnitOfMeasure(
                                        unitOfMeasureRepository.findByAbbreviation(product.getUnitOfMeasure()));
                            });
                    productInvoice.setInvoice(invoice);
                    productInvoice.setRawProduct(rawProduct);
                    return productInvoiceRepository.save(productInvoice);
                })
                .toList();
    }

}
