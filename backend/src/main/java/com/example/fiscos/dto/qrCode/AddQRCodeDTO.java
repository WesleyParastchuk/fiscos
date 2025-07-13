package com.example.fiscos.dto.qrCode;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class AddQRCodeDTO {

    @NotEmpty(message = "A lista de links não pode estar vazia.")
    @JsonProperty("links")
    private List<@URL(message = "O link fornecido não é uma URL válida.") String> links;

    @NotNull(message = "O ID do usuário não pode estar vazio.")
    @Positive(message = "O ID do usuário deve ser um valor positivo.")
    @JsonProperty("userId")
    private Long userId;
}
