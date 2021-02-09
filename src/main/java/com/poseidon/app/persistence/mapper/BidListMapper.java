package com.poseidon.app.persistence.mapper;

import com.poseidon.app.config.MapperConfig;
import com.poseidon.app.model.BidList;
import com.poseidon.app.persistence.entity.BidListEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Scope;

@Mapper(config = MapperConfig.class)
@Scope("singleton")
public interface BidListMapper {
    BidList toBidList(BidListEntity userEntity);

    BidListEntity fromBidList(BidList userEntity);
}
