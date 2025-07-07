package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Links;

public interface LinkRepository extends JpaRepository<Links, Long> {
}
