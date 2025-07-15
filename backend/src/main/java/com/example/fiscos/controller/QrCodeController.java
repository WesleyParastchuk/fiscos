package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.dto.qrCode.AddQRCodeDTO;
import com.example.fiscos.dto.rawProduct.RawProductDTO;
import com.example.fiscos.dto.supplier.SupplierDTO;
import com.example.fiscos.mapper.RawProductMapper;
import com.example.fiscos.mapper.SupplierMapper;
import com.example.fiscos.repository.RawProductRepository;
import com.example.fiscos.repository.SupplierRepository;
import com.example.fiscos.service.NFeService;
import com.example.fiscos.service.QrCodeService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeService qrCodeService;
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;
    private final RawProductRepository rawProductRepository;
    private final RawProductMapper rawProductMapper;
    private final NFeService nfeService;

    QrCodeController(QrCodeService qrCodeService, SupplierRepository supplierRepository,
            SupplierMapper supplierMapper, RawProductRepository rawProductRepository,
            RawProductMapper rawProductMapper,
            NFeService nfeService) {
        this.qrCodeService = qrCodeService;
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
        this.rawProductRepository = rawProductRepository;
        this.rawProductMapper = rawProductMapper;
        this.nfeService = nfeService;
    }

    @PostMapping
    public ResponseEntity<?> addLinks(@RequestBody @Valid AddQRCodeDTO dto) {
        nfeService.saveAll(dto.getUserId(), dto.getLinks());
        return ResponseEntity.status(201).body(dto);
    }

    @GetMapping("/supplier")
    public List<SupplierDTO> getMethodName() {
        return supplierRepository.findAll().stream()
                .map(
                        supplier -> supplierMapper.toDto(
                                supplier))
                .toList();
    }

    @GetMapping("/rawProduct")
    public List<RawProductDTO> getRawProduct() {
        return rawProductRepository.findAll().stream().map(
                rawProduct -> rawProductMapper.toDto(rawProduct)

        ).toList();
    }

}
