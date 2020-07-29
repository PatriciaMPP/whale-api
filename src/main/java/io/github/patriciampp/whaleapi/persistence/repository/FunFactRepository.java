package io.github.patriciampp.whaleapi.persistence.repository;

import io.github.patriciampp.whaleapi.persistence.model.FunFact;
import org.springframework.data.repository.CrudRepository;

public interface FunFactRepository extends CrudRepository <FunFact, Integer> {
}
