package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
