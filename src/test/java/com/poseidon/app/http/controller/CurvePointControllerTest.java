package com.poseidon.app.http.controller;

import com.poseidon.app.model.CurvePoint;
import com.poseidon.app.persistence.entity.CurvePointEntity;
import com.poseidon.app.service.CurvePointService;
import lombok.Getter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(CurvePointController.class)
class CurvePointControllerTest extends AbstractCrudControllerTest<CurvePointService, CurvePoint, CurvePointEntity, Long> {
    @MockBean
    private @Getter CurvePointService service;

    public CurvePointControllerTest() {
        super("/curvePoint", CurvePoint.class, Long.class);
    }

    @Override
    protected CurvePoint getModelA() {
        return CurvePoint.builder()
                .id(1L)
                .curveId(2L)
                .term(3D)
                .value(4D)
                .build();
    }

    @Override
    protected CurvePoint getModelB() {
        return CurvePoint.builder()
                .id(3517L)
                .curveId(484607L)
                .term(4.8974702D)
                .value(1.712767D)
                .build();
    }
}
