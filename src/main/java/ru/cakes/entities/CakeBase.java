package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CakeBase { //TODO: Enum?

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Cake> cakes;

}
