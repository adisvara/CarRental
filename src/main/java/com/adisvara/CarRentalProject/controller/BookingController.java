package com.adisvara.CarRentalProject.controller;

import com.adisvara.CarRentalProject.model.Booking;
import com.adisvara.CarRentalProject.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> getAllBooking(){
        return ResponseEntity.ok(bookingService.getALlBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id){
        return bookingService.getBookingById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking){
        return ResponseEntity.ok(bookingService.addBooking(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @Valid  @RequestBody Booking booking){
        Booking updatedBooking = bookingService.updateBooking(id,booking);

        return updatedBooking != null ? ResponseEntity.ok(updatedBooking): ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id){
        if (bookingService.getBookingById(id).isPresent()){
            bookingService.deleteBooking(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
