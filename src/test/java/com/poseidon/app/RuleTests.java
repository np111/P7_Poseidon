package com.poseidon.app;

import com.poseidon.app.persistence.entity.RuleNameEntity;
import com.poseidon.app.persistence.repository.RuleNameRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RuleTests {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    @Test
    public void ruleTest() {
        RuleNameEntity rule = new RuleNameEntity("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

        // Save
        rule = ruleNameRepository.save(rule);
        assertNotNull(rule.getId());
        assertEquals(rule.getName(), "Rule Name");

        // Update
        rule.setName("Rule Name Update");
        rule = ruleNameRepository.save(rule);
        assertEquals(rule.getName(), "Rule Name Update");

        // Find
        List<RuleNameEntity> listResult = ruleNameRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Long id = rule.getId();
        ruleNameRepository.delete(rule);
        Optional<RuleNameEntity> ruleList = ruleNameRepository.findById(id);
        assertFalse(ruleList.isPresent());
    }
}
