package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
