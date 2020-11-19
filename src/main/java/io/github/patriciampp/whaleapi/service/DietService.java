package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.Diet;
import io.github.patriciampp.whaleapi.persistence.repository.DietRepository;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietService {

    //private Set<Diet> diets;

    @Autowired
    private DietRepository dietRepository;

    public Iterable<Diet> getAll(){
        return dietRepository.findAll();
    }

    public Diet findById(int dietId){
        return dietRepository.findById(dietId).get();
    }

    public void deleteAll(){
        dietRepository.deleteAll();
    }

    public Boolean deleteById(int dietId){

        Diet dietToDelete = findById(dietId);
        dietRepository.delete(dietToDelete);
        return dietRepository.existsById(dietId);
    }

    public Diet add(Diet diet){
        dietRepository.save(diet);
        return diet;
    }

    public Diet update(Diet diet, JSONObject dietJSON){

        String dietText = dietJSON.get("diet").toString();
        diet.setDiet(dietText);

        dietRepository.save(diet);
        return diet;
    }

}
