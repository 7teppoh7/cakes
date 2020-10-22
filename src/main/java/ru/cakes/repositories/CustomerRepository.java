package ru.cakes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cakes.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}
