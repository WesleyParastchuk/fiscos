package com.example.fiscos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.fiscos.model.QRCode;
import com.example.fiscos.repository.QrCodeRepository;

@Service
public class QrCodeService {

    private final QrCodeRepository qrCodeRepository;

    public QrCodeService(QrCodeRepository qrCodeRepository) {
        this.qrCodeRepository = qrCodeRepository;
    }

    public List<QRCode> getAllQrCodes() {
        return qrCodeRepository.findAll();
    }

    public QRCode save(String link) {
        QRCode qrCode = new QRCode();
        qrCode.setLink(link);
        return qrCodeRepository.save(qrCode);
    }

    public List<String> removeDuplicateLinks(List<String> links) {
        return links.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
