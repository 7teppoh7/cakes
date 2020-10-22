package ru.cakes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cakes.entities.Characteristic;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {
}
