package com.example.fiscos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos")
public class ProcessedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @OneToMany(mappedBy = "processedProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInvoice> productInvoices;

    @OneToMany(mappedBy = "processedProduct", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductClassification> productClassifications;

    @OneToMany(mappedBy = "processedProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserProductClassification> userClassifications;

    @OneToMany(mappedBy = "processedProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductTagUser> productTagAssociations;

    @OneToMany(mappedBy = "processedProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RawProduct> rawProducts;
}
