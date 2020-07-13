package io.github.patriciampp.whaleapi.model;

public interface WhaleInterface {

    // getter methods for WhaleSpecie Class

    Integer getId();

    String getSpecieName();

    String getLatinName();

    String getLifeSpan();

    String getDescription();

    Double getSize();

    Double getWeight();


    // setter methods for WhaleSpecie Class

    void setId(Integer id);

    void setSpecieName(String specieName);

    void setLatinName(String latinName);

    void setLifeSpan(String lifeSpan);

    void setDescription(String description);

    void setSize(Double size);

    void setWeight(Double weight);


}
