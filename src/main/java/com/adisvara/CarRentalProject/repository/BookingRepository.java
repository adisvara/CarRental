package com.adisvara.CarRentalProject.repository;

import com.adisvara.CarRentalProject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
