package com.example.fiscos.dto.external.nfeApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierNFeDTO {
    @NotBlank(message = "Nome do emitente não pode estar vazio.")
    @JsonProperty("name")
    private String name;

    @NotBlank(message = "CNPJ não pode estar vazio.")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos.")
    @JsonProperty("cnpj")
    private String cnpj;

    @NotNull(message = "Endereço do emitente não pode ser nulo.")
    @JsonProperty("address")
    private AddressNFeDTO address;
}
