package com.poseidon.app.persistence.repository;

import com.poseidon.app.persistence.entity.TradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<TradeEntity, Long> {
}
