package com.adisvara.CarRentalProject;

import com.adisvara.CarRentalProject.model.Booking;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class BookingValidationTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidDateRange() {
        // Valid booking
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.now().plusDays(1));
        booking.setEndDate(LocalDate.now().plusDays(2));
        booking.setTotalPrice(100.0);

        var violations = validator.validate(booking);
        assertTrue(violations.isEmpty(), "Valid date range should not trigger validation errors");
    }

    @Test
    public void testInvalidDateRange() {
        // Invalid booking (endDate before startDate)
        Booking booking = new Booking();
        booking.setStartDate(LocalDate.now().plusDays(2));
        booking.setEndDate(LocalDate.now().plusDays(1));
        booking.setTotalPrice(100.0);

        var violations = validator.validate(booking);
        assertFalse(violations.isEmpty(), "Invalid date range should trigger validation errors");
    }

    @Test
    public void testNullDates() {
        // Invalid booking (null dates)
        Booking booking = new Booking();
        booking.setStartDate(null);
        booking.setEndDate(null);
        booking.setTotalPrice(100.0);

        var violations = validator.validate(booking);
        assertFalse(violations.isEmpty(), "Null dates should trigger validation errors");
    }
}
