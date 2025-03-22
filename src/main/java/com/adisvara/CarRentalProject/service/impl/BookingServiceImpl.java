package com.adisvara.CarRentalProject.service.impl;

import com.adisvara.CarRentalProject.model.Booking;
import com.adisvara.CarRentalProject.model.Car;
import com.adisvara.CarRentalProject.repository.BookingRepository;
import com.adisvara.CarRentalProject.repository.CarRepository;
import com.adisvara.CarRentalProject.repository.CustomerRepository;
import com.adisvara.CarRentalProject.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.bookingRepository = bookingRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Booking> getALlBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Booking addBooking(Booking booking) {
        Car car = carRepository.findById(booking.getCar().getId())
                .orElseThrow(() -> new IllegalStateException("Car with ID " + booking.getCar().getId() + " not found."));
        
        if (!customerRepository.existsById(booking.getCustomer().getId())) {
            throw new IllegalStateException("Customer with ID " + booking.getCustomer().getId() + " not found.");
        }
        
        if (!isCarAvailable(booking.getCar().getId(), booking)) {
            throw new IllegalStateException("Car not available for selected dates");
        }
        
        // Set initial status
        booking.setStatus("PENDING");
        booking.setCar(car);
        booking.calculateTotalPrice();
        
        // Validate dates
        if (booking.getStartDate().isAfter(booking.getEndDate())) {
            throw new IllegalStateException("Start date cannot be after end date");
        }
        
        if (booking.getStartDate().isBefore(LocalDate.now())) {
            throw new IllegalStateException("Cannot book for past dates");
        }
        
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long id, Booking booking) {
        return bookingRepository.findById(id).map(existingBooking -> {
            // Validate customer
            if (!customerRepository.existsById(booking.getCustomer().getId())) {
                throw new IllegalStateException("Customer with ID " + booking.getCustomer().getId() + " not found.");
            }
            
            // Validate car
            Car car = carRepository.findById(booking.getCar().getId())
                    .orElseThrow(() -> new IllegalStateException("Car with ID " + booking.getCar().getId() + " not found."));
            
            // Only allow updates if booking is not cancelled
            if ("CANCELLED".equals(existingBooking.getStatus())) {
                throw new IllegalStateException("Cannot update cancelled booking");
            }
            
            existingBooking.setCustomer(booking.getCustomer());
            existingBooking.setCar(car);
            existingBooking.setStartDate(booking.getStartDate());
            existingBooking.setEndDate(booking.getEndDate());
            existingBooking.setStatus(booking.getStatus());
            existingBooking.calculateTotalPrice();
            
            return bookingRepository.save(existingBooking);
        }).orElseThrow(() -> new IllegalArgumentException("Booking with " + id + " not found"));
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public boolean isCarAvailable(Long carId, Booking booking) {
        List<Booking> bookings = bookingRepository.findAll();

        for(Booking existingBooking: bookings){
            if(existingBooking.getCar().getId().equals(carId)){
                LocalDate startDate = booking.getStartDate();
                LocalDate endDate = booking.getEndDate();

                if(startDate.isBefore(existingBooking.getEndDate()) && endDate.isAfter(existingBooking.getStartDate())){
                    return false;
                }
            }
        }

        return true;
    }
}
