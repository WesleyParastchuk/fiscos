package com.example.fiscos.dto.nfeApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteNFeDTO {

    @NotNull(message = "Emitente não pode ser nulo.")
    @JsonProperty("supplier")
    private SupplierNFeDTO supplier;

    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    @JsonProperty("products")
    private List<@Valid RawProductNFeDTO> products;

    @NotNull(message = "Resumo não pode ser nulo.")
    @JsonProperty("summary")
    private SummaryNFeDTO summary;

    @NotNull(message = "Impostos não podem ser nulos.")
    @JsonProperty("tax")
    private TaxNFeDTO tax;

    @NotNull(message = "Informações gerais não podem ser nulas.")
    @JsonProperty("generalInfo")
    private GeneralInfoNFeDTO generalInfo;
}
