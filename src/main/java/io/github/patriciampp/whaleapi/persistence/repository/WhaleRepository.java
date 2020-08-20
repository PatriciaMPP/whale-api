package io.github.patriciampp.whaleapi.persistence.repository;

import io.github.patriciampp.whaleapi.persistence.model.Whale;
import org.springframework.data.repository.CrudRepository;

public interface WhaleRepository extends CrudRepository <Whale, Integer> {
}
