package com.example.fiscos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "classificacao")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "nome_resumido", nullable = false)
    private String shortName;

    @Column(name = "descricao", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pai", nullable = true)
    private Classification parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Classification> children;

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProductClassification> productClassifications;

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserProductClassification> userClassifications;

    @Column(name = "ativo", nullable = false)
    private boolean active = true;

    public Classification(String name, String shortName, String description) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
    }

    public Classification(String name, String shortName, String description, Classification parent) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.parent = parent;
    }

    public Classification(String name, String shortName, String description, Classification parent, boolean active) {
        this.name = name;
        this.shortName = shortName;
        this.description = description;
        this.parent = parent;
        this.active = active;
    }

}
