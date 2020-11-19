package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.FunFact;
import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.persistence.repository.FunFactRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FunFactService {

    //private Set<FunFact> funFacts;

    @Autowired
    private FunFactRepository funFactRepository;

    @Autowired
    private WhaleService whaleService;

    public Iterable<FunFact> getAll(){

        return funFactRepository.findAll();
    }

    public FunFact findById(int funFactId){

        return funFactRepository.findById(funFactId).get();
    }

    public void deleteAll(){
        funFactRepository.deleteAll();
    }

    public Boolean deleteById (int funFactId) {

        FunFact funFactToDelete = findById(funFactId);
        funFactRepository.delete(funFactToDelete);
        return !funFactRepository.existsById(funFactId);
    }

    public FunFact add(FunFact funFact){
        funFactRepository.save(funFact);
        return funFact;
    }

    public FunFact update (FunFact funFact, JSONObject funFactJSON) {

        String funFactText = funFactJSON.get("funFact").toString();

        funFact.setFunFactText(funFactText);

        Whale whale = whaleService.findById((Integer) funFactJSON.get("whaleId"));
        funFact.setWhale(whale);

        funFactRepository.save(funFact);

        return funFact;
    }


    //isJSONValid
    // parseId
    //create
    //update
    //add
    //findById
    //find
    //delete

}
