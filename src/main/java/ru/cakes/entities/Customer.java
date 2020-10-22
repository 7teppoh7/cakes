package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String lastName;

    private String firstName;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Cake> cakes = new HashSet<>();

}
