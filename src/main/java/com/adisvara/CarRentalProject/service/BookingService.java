package com.adisvara.CarRentalProject.service;

import com.adisvara.CarRentalProject.model.Booking;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<Booking> getALlBookings();
    Optional<Booking> getBookingById(Long id);
    Booking addBooking(Booking booking);
    Booking updateBooking(Long id, Booking booking);
    void deleteBooking(Long id);

    boolean isCarAvailable(Long carId, Booking booking);

}
