package com.example.fiscos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.fiscos.model.QRCode;
import com.example.fiscos.repository.QrCodeRepository;

@Service
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;

    public QrCodeService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public QRCode save(String link) {
        QRCode qrCode = new QRCode();
        qrCode.setLink(link);
        return qrCodeRepository.save(qrCode);
    }

    public void checkDuplicateLinks(List<String> links) {
        if (qrCodeRepository.existsByLinkIn(links)) {
            throw new IllegalArgumentException("Alguns links já existem.");
        }
    }
}
