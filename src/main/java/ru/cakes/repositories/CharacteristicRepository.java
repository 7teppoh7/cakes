package ru.cakes.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.cakes.entities.Characteristic;

public interface CharacteristicRepository extends CrudRepository<Characteristic, Integer> {

    @Query(value = "select count(characteristics_id) from cake_characteristic where characteristics_id = :id", nativeQuery = true)
    Integer countById(@Param("id") Integer id);
}
