package com.example.fiscos.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fiscos.backupGenerator.repository.BackupRepository;
import com.example.fiscos.dto.qrCode.AddQRCodeDTO;
import com.example.fiscos.model.QRCode;
import com.example.fiscos.repository.QrCodeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Autowired
    private BackupRepository backupRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public Boolean addQRCode(AddQRCodeDTO links) {
        try {
            List<String> linkList = links.getLinks();

            for (String link : linkList) {
                QRCode invoice = new QRCode();
                invoice.setLink(link);
                qrCodeRepository.save(invoice);
            }
            return true;
        } catch (Exception e) {
            Logger.getLogger(QrCodeService.class.getName()).log(Level.SEVERE, "Error saving links", e);
            return false;
        }

    }

    public List<QRCode> listQRCodes() {
        return qrCodeRepository.findAll();
    }

    @Transactional
    public void deleteQrCodes() {
        List<QRCode> allQrCodes = qrCodeRepository.findAll();

        if (allQrCodes.isEmpty()) {
            return;
        }

        try {
            JsonNode jsonNode = objectMapper.valueToTree(allQrCodes);

            backupRepository.saveBackup("qrCodes", jsonNode);

            qrCodeRepository.deleteAll();

        } catch (IOException e) {
            throw new RuntimeException("Falha ao criar backup, exclusão abortada.", e);
        }
    }

}
