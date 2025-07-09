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
import jakarta.persistence.GenerationType;

@Entity
@Data
@Table(name = "etiqueta")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Column(name = "descricao", nullable = false)
    private String description;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<ProductTagUser> productTagAssociations;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_usuario", nullable = true)
    private User createdBy;

}
