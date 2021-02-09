package com.poseidon.app.persistence.mapper;

import com.poseidon.app.config.MapperConfig;
import com.poseidon.app.model.RuleName;
import com.poseidon.app.persistence.entity.RuleNameEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Scope;

@Mapper(config = MapperConfig.class)
@Scope("singleton")
public interface RuleNameMapper extends CrudMapper<RuleName, RuleNameEntity> {
}
