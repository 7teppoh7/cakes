package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cake {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    private CakeBase cakeBase;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Decoration> decorations;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Characteristic> characteristics;

}
