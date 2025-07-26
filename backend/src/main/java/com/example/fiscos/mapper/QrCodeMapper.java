package com.example.fiscos.mapper;

import org.springframework.stereotype.Component;

import com.example.fiscos.dto.qrCode.QrCodeDTO;
import com.example.fiscos.model.QRCode;

@Component
public class QrCodeMapper {

    public QrCodeDTO toDto(QRCode qrCode) {
        QrCodeDTO dto = new QrCodeDTO();
        dto.setId(qrCode.getId());
        dto.setLink(qrCode.getLink());
        return dto;
    }

}
