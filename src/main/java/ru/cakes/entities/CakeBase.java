package ru.cakes.entities;

import lombok.*;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CakeBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cakeBase")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Cake> cakes = new HashSet<>();

    public CakeBase(String name){
        this.name = name;
    }

}
