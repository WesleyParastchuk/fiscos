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
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Classification> customClassifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserProductClassification> productClassifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProductTagUser> productTagAssociations;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Tag> createdTags;

    public User(String name) {
        this.name = name;
    }
}
