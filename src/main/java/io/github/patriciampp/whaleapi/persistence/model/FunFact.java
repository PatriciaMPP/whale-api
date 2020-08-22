package io.github.patriciampp.whaleapi.persistence.model;

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
public class FunFact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String funFactText;

    @ManyToOne(
            cascade = { CascadeType.ALL },
            fetch = FetchType.EAGER
    )
    private Whale whale;

    public FunFact (String funFactText, Whale whale) {
        this.funFactText = funFactText;
        this.whale = whale;
    }

}
