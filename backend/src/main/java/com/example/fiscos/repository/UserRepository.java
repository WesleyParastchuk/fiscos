package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
