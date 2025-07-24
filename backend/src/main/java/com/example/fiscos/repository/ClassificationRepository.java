package com.example.fiscos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.fiscos.model.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
    Classification findByNameAndShortNameAndDescription(String name, String shortName, String description);

    @Query("SELECT c FROM Classification c WHERE c.id NOT IN (SELECT DISTINCT p.parent.id FROM Classification p WHERE p.parent IS NOT NULL)")
    List<Classification> findAllLeafClassifications();


}
