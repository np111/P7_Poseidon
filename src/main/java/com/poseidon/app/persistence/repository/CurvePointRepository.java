package com.poseidon.app.persistence.repository;

import com.poseidon.app.persistence.entity.CurvePointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurvePointRepository extends JpaRepository<CurvePointEntity, Long> {
}
