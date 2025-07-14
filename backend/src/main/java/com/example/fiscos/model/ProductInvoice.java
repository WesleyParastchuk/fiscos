package com.example.fiscos.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "produtos_nota_fiscal")
public class ProductInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantity;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "produto_bruto_id", nullable = false)
    private GrossProduct grossProduct;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "unidade_medida_id", nullable = false)
    private UnitOfMeasure unitOfMeasure;
}
