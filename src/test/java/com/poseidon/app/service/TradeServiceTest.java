package com.poseidon.app.service;

import com.poseidon.app.model.Trade;
import com.poseidon.app.persistence.entity.TradeEntity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradeServiceTest extends AbstractCrudServiceTest<TradeService, Trade, TradeEntity, Long> {
    public TradeServiceTest() {
        super(TradeService.class, Trade.class, TradeEntity.class, Long.class);
    }

    @Override
    protected void prepareCreateAssertion(Trade excepted) {
        excepted.setCreationDate(timeService.nowSecs());
    }

    @Override
    protected void prepareUpdateAssertion(Trade excepted, Trade current) {
        excepted.setCreationDate(current == null ? null : current.getCreationDate());
    }
}
