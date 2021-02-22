package com.poseidon.app.service;

import com.poseidon.app.model.RuleName;
import com.poseidon.app.persistence.entity.RuleNameEntity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RuleNameServiceTest extends AbstractCrudServiceTest<RuleNameService, RuleName, RuleNameEntity, Long> {
    public RuleNameServiceTest() {
        super(RuleNameService.class, RuleName.class, RuleNameEntity.class, Long.class);
    }
}
