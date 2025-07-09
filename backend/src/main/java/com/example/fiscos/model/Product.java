package com.example.fiscos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "produtos")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_medida_id", nullable = false)
    private UnitOfMeasure unitOfMeasure;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInvoice> productInvoices;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductClassification> productClassifications;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<UserProductClassification> userClassifications;

}

