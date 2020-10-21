package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

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
    private Collection<Characteristic> characteristics;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Cake> cakes;

}
