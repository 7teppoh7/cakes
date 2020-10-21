package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

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
    private Collection<Cake> cakes;

}
