package ru.cakes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.cakes.entities.Decoration;

public interface DecorationRepository extends CrudRepository<Decoration, Integer> {

    Decoration findByName(String name);
}
