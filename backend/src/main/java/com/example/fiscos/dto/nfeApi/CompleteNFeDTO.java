package com.example.fiscos.dto.nfeApi;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteNFeDTO {

    @NotNull(message = "Emitente não pode ser nulo.")
    private SupplierNFeDTO supplier;

    @NotEmpty(message = "A lista de produtos não pode estar vazia.")
    private List<@Valid RawProductNFeDTO> products;

    @NotNull(message = "Resumo não pode ser nulo.")
    private SummaryNFeDTO summary;

    @NotNull(message = "Impostos não podem ser nulos.")
    private TaxNFeDTO tax;

    @NotNull(message = "Informações gerais não podem ser nulas.")
    private GeneralInfoNFeDTO generalInfo;
}
