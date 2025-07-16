package com.example.fiscos.model.mongo;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RawProductBackup {

    @Field("nome")
    private String name;

    @Field("codigo")
    private String code;

}