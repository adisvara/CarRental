package com.adisvara.CarRentalProject.repository;

import com.adisvara.CarRentalProject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
