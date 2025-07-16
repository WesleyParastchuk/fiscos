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
@Table(name = "produtos_brutos")
public class RawProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "codigo")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    private ProcessedProduct processedProduct;

    @OneToMany(mappedBy = "rawProduct", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductInvoice> productInvoices;
}
