package com.poseidon.app.service;

import com.poseidon.app.model.Rating;
import com.poseidon.app.persistence.entity.RatingEntity;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingServiceTest extends AbstractCrudServiceTest<RatingService, Rating, RatingEntity, Long> {
    public RatingServiceTest() {
        super(RatingService.class, Rating.class, RatingEntity.class, Long.class);
    }
}
