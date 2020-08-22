package io.github.patriciampp.whaleapi.persistence.model;

import javax.persistence.*;

@Entity
public class Whale extends WhaleAbstract {

    public Whale(){}

    public Whale(String specieName, String latinName, String lifeSpan, String description, Double size, Double weight) {
        super(specieName, latinName, lifeSpan, description, size, weight);
    }

}
