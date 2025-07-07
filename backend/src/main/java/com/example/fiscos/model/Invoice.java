package com.example.fiscos.model;

import java.math.BigDecimal;
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
@Table(name = "notas_fiscais")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_emissao", nullable = false)
    private String issueDate;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "invoice")
    private List<Tax> taxes;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<ProductInvoice> productInvoices;

}
