package com.example.springsecurity.reposiroty;

import com.example.springsecurity.entities.Role;
import com.example.springsecurity.utils.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(ERole name);
}
