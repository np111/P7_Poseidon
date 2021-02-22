package com.poseidon.app.service;

import com.poseidon.app.model.BidList;
import com.poseidon.app.persistence.entity.BidListEntity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BidListServiceTest extends AbstractCrudServiceTest<BidListService, BidList, BidListEntity, Long> {
    public BidListServiceTest() {
        super(BidListService.class, BidList.class, BidListEntity.class, Long.class);
    }

    @Override
    protected void prepareCreateAssertion(BidList excepted) {
        excepted.setCreationDate(timeService.nowSecs());
    }

    @Override
    protected void prepareUpdateAssertion(BidList excepted, BidList current) {
        excepted.setCreationDate(current == null ? null : current.getCreationDate());
    }
}
