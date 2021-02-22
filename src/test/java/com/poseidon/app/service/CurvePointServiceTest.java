package com.poseidon.app.service;

import com.poseidon.app.model.CurvePoint;
import com.poseidon.app.persistence.entity.CurvePointEntity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CurvePointServiceTest extends AbstractCrudServiceTest<CurvePointService, CurvePoint, CurvePointEntity, Long> {
    public CurvePointServiceTest() {
        super(CurvePointService.class, CurvePoint.class, CurvePointEntity.class, Long.class);
    }

    @Override
    protected void prepareCreateAssertion(CurvePoint excepted) {
        excepted.setCreationDate(timeService.nowSecs());
    }

    @Override
    protected void prepareUpdateAssertion(CurvePoint excepted, CurvePoint current) {
        excepted.setCreationDate(current == null ? null : current.getCreationDate());
    }
}
