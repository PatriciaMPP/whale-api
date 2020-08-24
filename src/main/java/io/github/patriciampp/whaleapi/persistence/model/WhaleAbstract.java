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
    private String size;
    private String weight;

    @OneToMany(
            cascade = { CascadeType.ALL },
            mappedBy = "whale",
            fetch = FetchType.EAGER
    )
    private Set<FunFact> funFacts = new HashSet<>();

    @ManyToMany
    private Set<Diet> diets = new HashSet<>();

    public WhaleAbstract (String specieName, String latinName, String lifeSpan, String description, String size, String weight){
        this.specieName = specieName;
        this.latinName = latinName;
        this.lifeSpan = lifeSpan;
        this.description = description;
        this.size = size;
        this.weight = weight;
    }
}

