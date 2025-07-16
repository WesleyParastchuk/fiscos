package com.example.fiscos.model.mongo;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassificationBackup {

    private String id;

    @Field("nome")
    private String name;

    @Field("descricao")
    private String description;

    @Field("pai")
    private ClassificationBackup parent;

}
