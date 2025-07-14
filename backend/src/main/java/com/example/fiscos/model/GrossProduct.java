package com.example.fiscos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "produtos_brutos")
public class GrossProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "codigo", nullable = true)
    private String code;

    @OneToMany(mappedBy = "grossProduct", cascade = CascadeType.ALL)
    private List<ProductInvoice> productInvoices;

}
