package com.example.fiscos.dto.nfeApi;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddressDTO {

    @NotBlank(message = "Rua não pode estar vazia.")
    private String street;

    @NotBlank(message = "Número não pode estar vazio.")
    private String number;

    private String complement;

    @NotBlank(message = "Bairro não pode estar vazio.")
    private String district;

    @NotBlank(message = "Cidade não pode estar vazia.")
    private String city;

    @NotBlank(message = "UF não pode estar vazio.")
    @Size(min = 2, max = 2, message = "UF deve conter 2 caracteres.")
    private String uf;
}
