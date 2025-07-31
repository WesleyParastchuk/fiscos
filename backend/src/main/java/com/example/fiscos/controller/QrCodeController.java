package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.dto.qrCode.AddQRCodeDTO;
import com.example.fiscos.service.NFeService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final NFeService nfeService;

    QrCodeController(
            NFeService nfeService) {
        this.nfeService = nfeService;
    }

    @PostMapping
    public ResponseEntity<?> addLinks(@RequestBody @Valid AddQRCodeDTO dto) {
        nfeService.saveAll(dto.getUserId(), dto.getLinks());
        return ResponseEntity.status(201).body(dto);
    }

    @PostMapping("/log")
    public void postLog(@RequestBody @Valid AddQRCodeDTO dto) {

        System.out.println("Received log entries: " + dto.getLinks().get(0));
    }

}
