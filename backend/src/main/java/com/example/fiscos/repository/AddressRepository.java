package com.example.fiscos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fiscos.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
