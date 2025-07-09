package com.example.fiscos.dto.nfeApi;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class IssuerDTO {

    @NotBlank(message = "Nome do emitente não pode estar vazio.")
    private String name;

    @NotBlank(message = "CNPJ não pode estar vazio.")
    @Pattern(regexp = "\\d{14}", message = "CNPJ deve conter 14 dígitos numéricos.")
    private String cnpj;

    @NotNull(message = "Endereço do emitente não pode ser nulo.")
    private AddressDTO address;
}
