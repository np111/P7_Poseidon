package com.poseidon.app.service;

import com.poseidon.app.model.CurvePoint;
import com.poseidon.app.persistence.entity.CurvePointEntity;
import com.poseidon.app.persistence.mapper.CurvePointMapper;
import com.poseidon.app.persistence.repository.CurvePointRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class CurvePointService extends CrudService<CurvePoint, CurvePointEntity, Long> {
    private final @Getter CurvePointRepository repository;
    private final @Getter CurvePointMapper mapper;

    @Override
    protected void setModelId(CurvePoint user, Long id) {
        user.setId(id);
    }
}
