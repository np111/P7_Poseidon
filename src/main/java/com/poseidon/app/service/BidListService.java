package com.poseidon.app.service;

import com.poseidon.app.model.BidList;
import com.poseidon.app.persistence.entity.BidListEntity;
import com.poseidon.app.persistence.mapper.BidListMapper;
import com.poseidon.app.persistence.repository.BidListRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class BidListService extends CrudService<BidList, BidListEntity, Long> {
    private final @Getter BidListRepository repository;
    private final @Getter BidListMapper mapper;

    @Override
    protected void setModelId(BidList user, Long id) {
        user.setId(id);
    }
}
