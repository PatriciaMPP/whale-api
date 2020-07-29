package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.controller.FunFactController;
import io.github.patriciampp.whaleapi.persistence.model.FunFact;
import io.github.patriciampp.whaleapi.persistence.repository.FunFactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class FunFactService {

    private Set<FunFact> funFacts;

    @Autowired
    private FunFactRepository funFactRepository;

    public FunFact add(FunFact funFact){
        if(funFacts == null) {
            funFacts = new HashSet<>();
        }

        funFactRepository.save(funFact);
        funFacts.add(funFact);
        return funFact;
    }

    public Set<FunFact> getAll(){
        return funFacts;
    }

    public void delete(FunFact funFact) {
        funFacts.remove(funFact);
    }



}
