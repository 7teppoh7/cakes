package ru.cakes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cakes.entities.CakeBase;

public interface CakeBaseRepository extends CrudRepository<CakeBase, Integer> {

    CakeBase findByName(String name);
}
