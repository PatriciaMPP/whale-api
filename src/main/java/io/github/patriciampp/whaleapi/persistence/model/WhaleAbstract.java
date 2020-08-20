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
@MappedSuperclass
public abstract class WhaleAbstract{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    private String specieName;
    private String latinName;
    private String lifeSpan;
    private String description;
    private Double size;
    private Double weight;

    @OneToMany(
            cascade = { CascadeType.ALL },
            mappedBy = "whale",
            fetch = FetchType.EAGER
    )
    private Set<FunFact> funFacts = new HashSet<>();

    @OneToMany(
            cascade = { CascadeType.ALL },
            fetch = FetchType.EAGER
    )
    private Set<Diet> diets = new HashSet<>();

}

