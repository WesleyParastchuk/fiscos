package com.example.fiscos.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fiscos.backupGenerator.repository.BackupRepository;
import com.example.fiscos.dto.nfeApi.CompleteNFeDTO;
import com.example.fiscos.dto.qrCode.AddQRCodeDTO;
import com.example.fiscos.model.QRCode;
import com.example.fiscos.repository.QrCodeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class QrCodeService {

    @Autowired
    private NFeApiService nfeApiService;

    @Autowired
    private QrCodeRepository qrCodeRepository;

    @Autowired
    private BackupRepository backupRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public List<CompleteNFeDTO> addQRCode(AddQRCodeDTO links) {
        try {
            List<String> linkList = links.getLinks();
            List<CompleteNFeDTO> nfeList = new ArrayList<>();

            for (String link : linkList) {
                QRCode qrCode = new QRCode();
                qrCode.setLink(link);
                qrCodeRepository.save(qrCode);

                try {
                    nfeApiService.getNFeByUrl(link);
                    nfeList.add(nfeApiService.getNFeByUrl(link));
                } catch (Exception e) {
                    System.err.println("Erro ao registrar QR Code: " + e.getMessage());
                }
            }
            if (!nfeList.isEmpty()) {
                return nfeList;
            }

            throw new RuntimeException(
                    "QR Codes adicionados com sucesso, mas não foi possível obter NFe para todos os links.");
        } catch (Exception e) {
            System.err.println("Erro ao adicionar QR Code: " + e.getMessage());
            throw new RuntimeException("Erro ao adicionar QR Code.", e);
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
