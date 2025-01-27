package com.adisvara.CarRentalProject.service.impl;

import com.adisvara.CarRentalProject.model.Customer;
import com.adisvara.CarRentalProject.repository.CustomerRepository;
import com.adisvara.CarRentalProject.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if(existingCustomer.isPresent()){
            Customer updatedCustomer = existingCustomer.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPhoneNumber(customer.getPhoneNumber());

            return customerRepository.save(updatedCustomer);
        }

        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
