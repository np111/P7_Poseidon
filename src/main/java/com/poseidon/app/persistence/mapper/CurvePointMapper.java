package com.poseidon.app.persistence.mapper;

import com.poseidon.app.config.MapperConfig;
import com.poseidon.app.model.CurvePoint;
import com.poseidon.app.persistence.entity.CurvePointEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Scope;

@Mapper(config = MapperConfig.class)
@Scope("singleton")
public interface CurvePointMapper {
    CurvePoint toCurvePoint(CurvePointEntity userEntity);

    CurvePointEntity fromCurvePoint(CurvePoint userEntity);
}
