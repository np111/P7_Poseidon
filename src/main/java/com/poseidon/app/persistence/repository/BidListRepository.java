package com.poseidon.app.persistence.repository;

import com.poseidon.app.persistence.entity.BidListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidListRepository extends JpaRepository<BidListEntity, Long> {
}
