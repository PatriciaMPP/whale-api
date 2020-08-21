package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.persistence.repository.WhaleRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WhaleService {

    private Set<Whale> whales;
    private Whale whale;
    private int whaleId;

    @Autowired
    private WhaleRepository whaleRepository;

    public Set<Whale> getAll(){
        whaleRepository.findAll();
        return whales;
    }

    public Whale findById(int whaleId){
        whaleRepository.findById(whaleId);
        return whale;
    }

    public Whale add(Whale whale){
        if(whales == null) {
            whales = new HashSet<>();
        }

        whales.add(whale);
        whaleRepository.save(whale);
        return whale;
    }

    public Whale update(Whale whale, JSONObject whaleJSON){

        String specieName = whaleJSON.get("specieName").toString();
        String latinName = whaleJSON.get("latinName").toString();
        String lifeSpan = whaleJSON.get("lifeSpan").toString();
        String description = whaleJSON.get("description").toString();
        Double size = (Double) whaleJSON.get("size");
        Double weight = (Double) whaleJSON.get("weight");

        whale.setSpecieName(specieName);
        whale.setLatinName(latinName);
        whale.setLifeSpan(lifeSpan);
        whale.setDescription(description);
        whale.setSize(size);
        whale.setWeight(weight);

        whaleRepository.save(whale);
        return whale;
    }

    public Set<Whale> deleteAll(){
        whaleRepository.deleteAll();
        return whales;
    }

    public int deleteById(int whaleId){

        Whale whaleToDelete = findById(whaleId);

        whales.remove(whaleToDelete);
        whaleRepository.delete(whaleToDelete);
        return whaleId;
    }







}
