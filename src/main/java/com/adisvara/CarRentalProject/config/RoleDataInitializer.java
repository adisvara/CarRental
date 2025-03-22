package com.adisvara.CarRentalProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.adisvara.CarRentalProject.model.ERole;
import com.adisvara.CarRentalProject.model.Role;
import com.adisvara.CarRentalProject.repository.RoleRepository;

@Configuration
public class RoleDataInitializer {
    
    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            // Initialize roles if they don't exist
            for (ERole role : ERole.values()) {
                if (roleRepository.findByName(role).isEmpty()) {
                    roleRepository.save(new Role(role));
                    System.out.println("Created role: " + role);
                }
            }
            
            System.out.println("Roles initialization completed.");
        };
    }
} 