package com.poseidon.app.persistence.repository;

import com.poseidon.app.persistence.entity.RuleNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleNameRepository extends JpaRepository<RuleNameEntity, Long> {
}
