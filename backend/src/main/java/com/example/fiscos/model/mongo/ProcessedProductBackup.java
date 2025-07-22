package com.example.fiscos.model.mongo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produtos")
public class ProcessedProductBackup {

    @Id
    private String id;

    @Field("data_criacao")
    private String creationDate;

    @Field("nome")
    private String name;

    @Field("produtos_brutos")
    private List<RawProductBackup> rawProducts;

    @Field("classificacoes")
    private List<ClassificationBackup> classifications;

}