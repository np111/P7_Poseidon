package com.poseidon.app.service;

import com.poseidon.app.model.Trade;
import com.poseidon.app.persistence.entity.TradeEntity;
import com.poseidon.app.persistence.mapper.TradeMapper;
import com.poseidon.app.persistence.repository.TradeRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class TradeService extends CrudService<Trade, TradeEntity, Long> {
    private final @Getter TradeRepository repository;
    private final @Getter TradeMapper mapper;

    @Override
    protected void setModelId(Trade user, Long id) {
        user.setId(id);
    }
}
