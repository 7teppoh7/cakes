package ru.cakes.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.cakes.entities.Decoration;

public interface DecorationRepository extends CrudRepository<Decoration, Integer> {

    Decoration findByName(String name);

    @Query(value = "select count(decorations_id) from cake_decoration where decorations_id = :id", nativeQuery = true)
    Integer countById(@Param("id") Integer id);
}
