package com.poseidon.app.persistence.mapper;

import com.poseidon.app.config.MapperConfig;
import com.poseidon.app.model.Trade;
import com.poseidon.app.persistence.entity.TradeEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Scope;

@Mapper(config = MapperConfig.class)
@Scope("singleton")
public interface TradeMapper extends CrudMapper<Trade, TradeEntity> {
}
