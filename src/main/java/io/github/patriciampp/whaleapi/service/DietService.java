package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.Diet;
import io.github.patriciampp.whaleapi.persistence.repository.DietRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DietService {

    private Set<Diet> diets;

    @Autowired
    private DietRepository dietRepository;

    public Set<Diet> getAll(){
        dietRepository.findAll();
        return diets;
    }

    public Diet findById(int dietId){
        return dietRepository.findById(dietId).get();
    }

    public Set<Diet> deleteAll(){
        dietRepository.deleteAll();
        return diets;
    }

    public Diet deleteById(int dietId){

        Diet dietToDelete = findById(dietId);
        dietRepository.delete(dietToDelete);
        return dietToDelete;

    }

    public Diet add(Diet diet){

        if(diets == null) {
            diets = new HashSet<>();
        }

        diets.add(diet);
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
