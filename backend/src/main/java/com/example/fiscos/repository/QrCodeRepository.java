package com.example.fiscos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.QRCode;

public interface QrCodeRepository extends JpaRepository<QRCode, Long> {
    boolean existsByLink(String link);

    boolean existsByLinkIn(List<String> links);
}
