package com.poseidon.app.persistence.mapper;

import com.poseidon.app.config.MapperConfig;
import com.poseidon.app.model.Rating;
import com.poseidon.app.persistence.entity.RatingEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Scope;

@Mapper(config = MapperConfig.class)
@Scope("singleton")
public interface RatingMapper extends CrudMapper<Rating, RatingEntity> {
}
