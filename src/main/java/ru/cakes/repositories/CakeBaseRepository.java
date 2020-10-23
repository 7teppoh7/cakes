package ru.cakes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cakes.entities.CakeBase;

interface CakeBaseRepository extends CrudRepository<CakeBase, Integer> {
}
