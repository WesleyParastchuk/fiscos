package com.example.fiscos.model;

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
import lombok.NoArgsConstructor;
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "classificacao")
@NoArgsConstructor
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descricao", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_pai", nullable = true)
    private Classification parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Classification> children;

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductClassification> productClassifications;

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL)
    private List<UserProductClassification> userClassifications;

    public Classification(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Classification(String name, String description, Classification parent) {
        this.name = name;
        this.description = description;
        this.parent = parent;
    }

}
