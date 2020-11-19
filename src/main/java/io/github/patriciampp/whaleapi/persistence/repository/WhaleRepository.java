package io.github.patriciampp.whaleapi.persistence.repository;

import io.github.patriciampp.whaleapi.persistence.model.Whale;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface WhaleRepository extends CrudRepository <Whale, Integer> {
    @Override
    ArrayList<Whale> findAll();
}
