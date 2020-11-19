package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.persistence.repository.WhaleRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WhaleService {

    @Autowired
    private WhaleRepository whaleRepository;

    public ArrayList<Whale> getAll(){
        return whaleRepository.findAll();
    }

    public Whale findById(int whaleId){
        return whaleRepository.findById(whaleId).get();
    }

    public void deleteAll(){
        whaleRepository.deleteAll();
    }

    public Boolean deleteById(int whaleId){

        Whale whaleToDelete = findById(whaleId);
        //whales.remove(whaleToDelete);
        whaleRepository.delete(whaleToDelete);
        return !whaleRepository.existsById(whaleId);
    }

    public Whale add(Whale whale){
        whaleRepository.save(whale);
        return whale;
    }

    public Whale update(Whale whale, JSONObject whaleJSON){

        String specieName = whaleJSON.get("specieName").toString();
        String latinName = whaleJSON.get("latinName").toString();
        String lifeSpan = whaleJSON.get("lifeSpan").toString();
        String description = whaleJSON.get("description").toString();
        String size = whaleJSON.get("size").toString();
        String weight = whaleJSON.get("weight").toString();

        whale.setSpecieName(specieName);
        whale.setLatinName(latinName);
        whale.setLifeSpan(lifeSpan);
        whale.setDescription(description);
        whale.setSize(size);
        whale.setWeight(weight);

        whaleRepository.save(whale);
        return whale;
    }


    public Iterable<Whale> findWhalesByMinSize(int requestedMinSize){
        Iterable<Whale> whalesList = getAll();
        ArrayList<Whale> minSizeWhales = new ArrayList<>();

        for(Whale whale : whalesList){
           int minSize =  extractMinSizeFromSize(whale.getSize());

           if(minSize >= requestedMinSize) {
               minSizeWhales.add(whale);
           }
        }

        return minSizeWhales;
    };

    // o que faz o metodo
    // o que deve receber
    // o que retorna
    // @Param size: String, "between 10 and 30 meters"
    public int extractMinSizeFromSize(String size){
        String[] sizeElements = size.split(" ");
        return Integer.parseInt(sizeElements[0]);
    }


}





