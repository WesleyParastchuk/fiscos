package com.example.fiscos.dto.qrCode;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QrCodeDTO {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("link")
    private String link;

}
