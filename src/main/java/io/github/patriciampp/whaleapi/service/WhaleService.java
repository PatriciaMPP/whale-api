package io.github.patriciampp.whaleapi.service;

import io.github.patriciampp.whaleapi.persistence.model.FunFact;
import io.github.patriciampp.whaleapi.persistence.model.Whale;
import io.github.patriciampp.whaleapi.persistence.repository.WhaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashSet;
import java.util.Set;

@Service
public class WhaleService {

    private Set<Whale> whales;
    private Whale whale;
    private Integer idWhale;

    @Autowired
    private WhaleRepository whaleRepository;

    public void add(Whale whale){
        if(whales == null) {
            whales = new HashSet<>();
        }

        whales.add(whale);
    }

    public Whale getById(Integer idWhale){
        return whale;
    }




}
