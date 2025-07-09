package com.example.fiscos.dto.nfeApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;

@Data
public class CompleteNFeDTO {

    @NotNull(message = "Emitente não pode ser nulo.")
    private IssuerDTO issuer;

    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    private List<@Valid ProductDTO> products;

    @NotNull(message = "Resumo não pode ser nulo.")
    private SummaryDTO summary;

    @NotNull(message = "Impostos não podem ser nulos.")
    private TaxDTO tax;

    @NotNull(message = "Informações gerais não podem ser nulas.")
    private GeneralInfoDTO generalInfo;
}
