package com.poseidon.app.http.controller;

import com.poseidon.app.model.BidList;
import com.poseidon.app.persistence.entity.BidListEntity;
import com.poseidon.app.service.BidListService;
import lombok.Getter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(BidListController.class)
class BidListControllerTest extends AbstractCrudControllerTest<BidListService, BidList, BidListEntity, Long> {
    @MockBean
    private @Getter BidListService service;

    public BidListControllerTest() {
        super("/bidList", BidList.class, Long.class);
    }

    @Override
    protected BidList getModelA() {
        return BidList.builder()
                .id(1L)
                .account("account")
                .type("type")
                .bidQuantity(1D)
                .build();
    }

    @Override
    protected BidList getModelB() {
        return BidList.builder()
                .id(21392L)
                .account("zPH3gdvyzP")
                .type("UeIQDEq1mg")
                .bidQuantity(3.241461D)
                .build();
    }
}
