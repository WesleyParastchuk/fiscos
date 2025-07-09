package com.example.fiscos.dto.nfeApi;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GeneralInfoDTO {

    @NotBlank(message = "Tipo de emissão não pode estar vazio.")
    private String emissionType;

    @Positive(message = "Número da nota fiscal deve ser positivo.")
    private int invoiceNumber;

    @Positive(message = "Série deve ser positiva.")
    private int series;

    @Positive(message = "Data de emissão deve ser um timestamp válido.")
    private long emissionDate;

    @NotBlank(message = "Protocolo de autorização não pode estar vazio.")
    private String authorizationProtocol;

    @Positive(message = "Data do protocolo deve ser um timestamp válido.")
    private long protocolDate;

    @NotBlank(message = "Ambiente não pode estar vazio.")
    private String environment;

    @NotBlank(message = "Versão XML não pode estar vazia.")
    private String xmlVersion;

    @NotBlank(message = "Versão XSLT não pode estar vazia.")
    private String xsltVersion;
}
