package com.example.fiscos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tipo_tributos")
public class TaxType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "abreviacao", nullable = false)
    private String abbreviation;

    @OneToMany(mappedBy = "taxType")
    private List<Tax> taxes;

    public TaxType(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }
}
