package com.poseidon.app.persistence.repository;

import com.poseidon.app.persistence.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<RatingEntity, Long> {
}
