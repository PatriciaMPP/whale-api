package io.github.patriciampp.whaleapi.persistence.repository;

import io.github.patriciampp.whaleapi.persistence.model.Diet;
import org.springframework.data.repository.CrudRepository;

public interface DietRepository extends CrudRepository <Diet, Integer> {
}
