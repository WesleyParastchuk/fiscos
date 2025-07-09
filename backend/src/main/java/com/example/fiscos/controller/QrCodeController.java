package com.example.fiscos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fiscos.dto.links.AddQRCodeDTO;
import com.example.fiscos.service.QrCodeService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<Boolean> addLinks(@RequestBody @Valid AddQRCodeDTO dto) {
        return ResponseEntity.status(201).body(qrCodeService.addQRCode(dto));
    }

    @GetMapping
    public ResponseEntity<?> getAllLinks() {
        return ResponseEntity.ok(qrCodeService.listQRCodes());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllLinks() {
        qrCodeService.deleteQrCodes();
        return ResponseEntity.noContent().build();
    }

}
