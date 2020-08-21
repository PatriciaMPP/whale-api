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

    private Set<FunFact> funFacts;

    @Autowired
    private FunFactRepository funFactRepository;

    @Autowired
    private WhaleService whaleService;

    public Set<FunFact> getAll(){

        funFactRepository.findAll();
        return funFacts;
    }

    public FunFact findById(int funFactId){

        return funFactRepository.findById(funFactId).get();
    }

    public FunFact add(FunFact funFact){
        if(funFacts == null) {
            funFacts = new HashSet<>();
        }

        funFacts.add(funFact);
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

    public Set<FunFact> deleteAll(){
        funFactRepository.deleteAll();
        return funFacts;
    }

    public int deleteById (int funFactId) {

        FunFact funFactToDelete = findById(funFactId);
        funFactRepository.delete(funFactToDelete);
        return funFactId;

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
