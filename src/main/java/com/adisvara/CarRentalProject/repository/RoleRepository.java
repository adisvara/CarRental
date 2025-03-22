package com.adisvara.CarRentalProject.repository;

import com.adisvara.CarRentalProject.model.ERole;
import com.adisvara.CarRentalProject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
} 