package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Decoration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Float price;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Characteristic> characteristics = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Cake> cakes = new HashSet<>();

}
