package io.github.patriciampp.whaleapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FunFacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String funFact;

    @ManyToOne(
            cascade = { CascadeType.ALL },
            fetch = FetchType.EAGER
    )
    private Whale whale;

}
