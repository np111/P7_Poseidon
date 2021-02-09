package com.poseidon.app.service;

import com.poseidon.app.model.Rating;
import com.poseidon.app.persistence.entity.RatingEntity;
import com.poseidon.app.persistence.mapper.RatingMapper;
import com.poseidon.app.persistence.repository.RatingRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Scope("singleton")
public class RatingService extends CrudService<Rating, RatingEntity, Long> {
    private final @Getter RatingRepository repository;
    private final @Getter RatingMapper mapper;

    @Override
    protected void setModelId(Rating user, Long id) {
        user.setId(id);
    }
}
