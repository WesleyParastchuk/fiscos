package com.example.fiscos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enderecos")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rua", nullable = false)
    private String street;

    @Column(name = "numero", nullable = false)
    private String number;

    @Column(name = "complemento", nullable = false)
    private String complement;

    @Column(name = "bairro", nullable = false)
    private String district;

    @Column(name = "cidade", nullable = false)
    private String city;

    @Column(name = "uf", nullable = false)
    private String uf;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private Supplier supplier;
}
