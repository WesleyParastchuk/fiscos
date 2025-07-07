package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.UserProductClassification;

public interface UserProductClassificationRepository extends JpaRepository<UserProductClassification, Long> {
}