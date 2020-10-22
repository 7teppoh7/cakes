package ru.cakes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cakes.entities.Cake;

public interface CakeRepository extends CrudRepository<Cake, Integer> {
}
