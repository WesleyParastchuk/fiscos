package com.example.fiscos.dto.invoice;

import java.util.List;

import com.example.fiscos.dto.product.ProductDTO;
import com.example.fiscos.dto.qrCode.QrCodeDTO;
import com.example.fiscos.dto.supplier.SupplierDTO;
import com.example.fiscos.dto.tax.TaxesDTO;
import com.example.fiscos.dto.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompleteInvoiceDTO extends InvoiceDTO {

    @JsonProperty("user")
    private UserDTO user;

    @JsonProperty("supplier")
    private SupplierDTO supplier;

    @JsonProperty("taxes")
    private TaxesDTO taxes;

    @JsonProperty("products")
    private List<ProductDTO> products;

    @JsonProperty("qrcode")
    private QrCodeDTO qrcode;
}
