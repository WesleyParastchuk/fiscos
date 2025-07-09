package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.QRCode;

public interface QrCodeRepository extends JpaRepository<QRCode, Long> {
}
