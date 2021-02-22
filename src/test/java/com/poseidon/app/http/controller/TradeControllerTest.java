package com.poseidon.app.http.controller;

import com.poseidon.app.model.Trade;
import com.poseidon.app.persistence.entity.TradeEntity;
import com.poseidon.app.service.TradeService;
import lombok.Getter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(TradeController.class)
class TradeControllerTest extends AbstractCrudControllerTest<TradeService, Trade, TradeEntity, Long> {
    @MockBean
    private @Getter TradeService service;

    public TradeControllerTest() {
        super("/trade", Trade.class, Long.class);
    }

    @Override
    protected Trade getModelA() {
        return Trade.builder()
                .id(1L)
                .account("account")
                .type("type")
                .buyQuantity(2D)
                .build();
    }

    @Override
    protected Trade getModelB() {
        return Trade.builder()
                .id(535856L)
                .account("tcuLDhiU7w")
                .type("WeENe5bmtP")
                .buyQuantity(1.84388D)
                .build();
    }
}
