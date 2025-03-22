package com.adisvara.CarRentalProject.model;

import com.adisvara.CarRentalProject.customAnnotation.ValidDateRange;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@ValidDateRange(message = "Start date should be before from END DATE")
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message= "Customer is required")
    @ManyToOne
    @JoinColumn(name ="customer_id", nullable = false)
    private Customer customer;

    @NotNull(message = "Car is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @NotNull(message = "Start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Start Date cannot be in the past")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "End date cannot be in the past")
    @Column(nullable = false)
    private LocalDate endDate;

    //@NotNull(message = "Total Price is required")
    //@DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than 0")
    //@Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private String status = "PENDING";

    @PrePersist
    @PreUpdate
    public void calculateTotalPrice(){
        if(car != null && startDate != null && endDate != null){
            System.out.println("Car object: " + car.getId()+" ,"+ car.getMake() +" ," +car.getYear());
            long days = ChronoUnit.DAYS.between(startDate,endDate);
            double pricePerDay = car.getPricePerDay();
            System.out.println("Calculating total price: pricePerDay=" + pricePerDay + ", days=" + days);

            this.totalPrice = pricePerDay * (days>0 ? days:1);
        }
    }

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "PENDING";
        }
        calculateTotalPrice();
    }

    @PreUpdate
    public void preUpdate() {
        calculateTotalPrice();
    }

    public Booking() {
    }

    public Booking(Long id, Customer customer, Car car, LocalDate startDate, LocalDate endDate, double totalPrice) {
        this.id = id;
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
