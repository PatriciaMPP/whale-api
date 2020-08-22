package io.github.patriciampp.whaleapi.persistence.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String diet;

    @ManyToMany(mappedBy = "diets")
    private Set<Whale> whales = new HashSet<>();

    public Diet(String diet){
        this.diet = diet;
    }

}
