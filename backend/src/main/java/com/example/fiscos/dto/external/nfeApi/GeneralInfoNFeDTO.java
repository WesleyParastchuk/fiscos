package com.example.fiscos.dto.external.nfeApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralInfoNFeDTO {

    @NotBlank(message = "Tipo de emissão não pode estar vazio.")
    @JsonProperty("emissionType")
    private String emissionType;

    @Positive(message = "Número da nota fiscal deve ser positivo.")
    @JsonProperty("invoiceNumber")
    private Integer invoiceNumber;

    @Positive(message = "Série deve ser positiva.")
    @JsonProperty("series")
    private Integer series;

    @Positive(message = "Data de emissão deve ser um timestamp válido.")
    @JsonProperty("emissionDate")
    private Long emissionDate;

    @NotBlank(message = "Protocolo de autorização não pode estar vazio.")
    @JsonProperty("authorizationProtocol")
    private String authorizationProtocol;

    @Positive(message = "Data do protocolo deve ser um timestamp válido.")
    @JsonProperty("protocolDate")
    private Long protocolDate;

    @NotBlank(message = "Ambiente não pode estar vazio.")
    @JsonProperty("environment")
    private String environment;

    @NotBlank(message = "Versão XML não pode estar vazia.")
    @JsonProperty("xmlVersion")
    private String xmlVersion;

    @NotBlank(message = "Versão XSLT não pode estar vazia.")
    @JsonProperty("xsltVersion")
    private String xsltVersion;
}
