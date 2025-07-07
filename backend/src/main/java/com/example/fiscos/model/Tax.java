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
@Table(name = "tributo")
public class Tax {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "valor", nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "tipo_tributo_id", nullable = false)
    private TaxType taxType;

    @ManyToOne
    @JoinColumn(name = "nota_fiscal_id", nullable = false)
    private Invoice invoice;
}
