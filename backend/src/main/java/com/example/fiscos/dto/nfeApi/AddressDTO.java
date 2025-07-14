package com.example.fiscos.dto.nfeApi;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AddressDTO {

    @NotBlank(message = "Rua não pode estar vazia.")
    @JsonProperty("street")
    private String street;

    @NotBlank(message = "Número não pode estar vazio.")
    @JsonProperty("number")
    private String number;

    @JsonProperty("complement")
    private String complement;

    @NotBlank(message = "Bairro não pode estar vazio.")
    @JsonProperty("district")
    private String district;

    @NotBlank(message = "Cidade não pode estar vazia.")
    @JsonProperty("city")
    private String city;

    @NotBlank(message = "UF não pode estar vazio.")
    @Size(min = 2, max = 2, message = "UF deve conter 2 caracteres.")
    @JsonProperty("uf")
    private String uf;
}
