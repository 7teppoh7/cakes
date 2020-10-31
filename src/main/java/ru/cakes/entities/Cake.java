package ru.cakes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
    private Set<Decoration> decorations = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Characteristic> characteristics = new HashSet<>();

    public Cake(String name, Float price, Customer customer) {
        this.name = name;
        this.price = price;
        this.customer = customer;
    }

}
