package com.poseidon.app.service;

import com.poseidon.app.model.RuleName;
import com.poseidon.app.persistence.entity.RuleNameEntity;
import com.poseidon.app.persistence.mapper.RuleNameMapper;
import com.poseidon.app.persistence.repository.RuleNameRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class RuleNameService extends CrudService<RuleName, RuleNameEntity, Long> {
    private final @Getter RuleNameRepository repository;
    private final @Getter RuleNameMapper mapper;

    @Override
    protected void setModelId(RuleName user, Long id) {
        user.setId(id);
    }
}
