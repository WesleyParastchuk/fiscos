package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findByNameAndShortNameAndDescription(String name, String shortName, String description);
}
