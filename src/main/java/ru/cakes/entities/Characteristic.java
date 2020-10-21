package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String subscription;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Decoration> decorations;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Cake> cakes;
}
