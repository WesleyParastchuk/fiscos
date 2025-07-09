package com.example.fiscos.model;

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
@Table(name = "produto_tag_usuario")
public class ProductTagUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produto", nullable = false)
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tag", nullable = false)
    private Tag tag;
}
