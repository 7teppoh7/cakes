package ru.cakes.services;

import org.springframework.stereotype.Service;
import ru.cakes.entities.Customer;
import ru.cakes.repositories.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean isCustomerValid(Customer customer) {
        return customer != null && !customer.getFirstName().trim().equals("") && !customer.getLastName().trim().equals("");
    }

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }
}
