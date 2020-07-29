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

    /*@Override
    public String getSpecieName() { return specieName; }

    @Override
    public String getLatinName() { return latinName; }

    @Override
    public String getLifeSpan() { return lifeSpan; }

    @Override
    public Double getSize() { return size; }

    @Override
    public Double getWeight() { return weight; }

    @Override
    public String getDescription() { return description; }


    @Override
    public void setSpecieName (String specieName) { this.specieName = specieName; }

    @Override
    public void setLatinName (String latinName) { this.latinName = latinName; }

    @Override
    public void setLifeSpan (String lifeSpan) { this.lifeSpan = lifeSpan; }

    @Override
    public void setSize (Double size) { this.size = size; }

    @Override
    public void setWeight(Double weight) { this.weight = weight; }

    @Override
    public void setDescription(String description) { this.description = description; }*/
}

