package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.FunFact;
import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.persistence.repository.FunFactRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FunFactService {

    private Set<FunFact> funFacts;

    @Autowired
    private FunFactRepository funFactRepository;

    @Autowired
    private WhaleService whaleService;

    public Set<FunFact> getAll(){
        return funFacts;
    }

    public FunFact add(FunFact funFact){
        if(funFacts == null) {
            funFacts = new HashSet<>();
        }

        funFactRepository.save(funFact);
        funFacts.add(funFact);
        return funFact;
    }

    public FunFact update (FunFact funFact, JSONObject funFactJSON) {

        String funFactText = funFactJSON.get("funFact").toString();

        funFact.setFunFactText(funFactText);

        Whale whale = whaleService.getById((Integer) funFactJSON.get("whaleId"));
        funFact.setWhale(whale);

        funFactRepository.save(funFact);

        return funFact;
    }

    public void delete(FunFact funFact) {
        funFacts.remove(funFact);
    }


    public FunFact findById(int id){
      return funFactRepository.findById(id).get();
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
