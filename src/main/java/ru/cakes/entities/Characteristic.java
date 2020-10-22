package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Characteristic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String subscription;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Decoration> decorations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Cake> cakes = new HashSet<>();
}
