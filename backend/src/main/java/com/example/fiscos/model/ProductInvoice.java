package com.example.fiscos.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produtos_nota_fiscal")
public class ProductInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "quantidade", nullable = false)
    private BigDecimal quantity;

    @Column(name = "preco_unitario", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "preco_total", nullable = false)
    private BigDecimal totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = true)
    private ProcessedProduct processedProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_bruto_id", nullable = false)
    private RawProduct rawProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_fiscal_id", nullable = false)
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_medida_id", nullable = false)
    private UnitOfMeasure unitOfMeasure;
}
