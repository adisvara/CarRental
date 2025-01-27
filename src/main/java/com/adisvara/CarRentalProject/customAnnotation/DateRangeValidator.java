package com.adisvara.CarRentalProject.customAnnotation;

import com.adisvara.CarRentalProject.model.Booking;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, Booking> {

    @Override
    public boolean isValid(Booking booking, ConstraintValidatorContext constraintValidatorContext) {
        // Return false if either date is null (already validated with @NotNull)
        if (booking.getStartDate() == null || booking.getEndDate() == null) {
            return false;
        }

        // Start date must not be in the past
        if (booking.getStartDate().isBefore(LocalDate.now())) {
            return false;
        }

        // End date must be after or equal to the start date
        return !booking.getEndDate().isBefore(booking.getStartDate());
    }
}
