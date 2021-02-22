package com.poseidon.app.http.controller;

import com.poseidon.app.model.Rating;
import com.poseidon.app.persistence.entity.RatingEntity;
import com.poseidon.app.service.RatingService;
import lombok.Getter;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(RatingController.class)
class RatingControllerTest extends AbstractCrudControllerTest<RatingService, Rating, RatingEntity, Long> {
    @MockBean
    private @Getter RatingService service;

    public RatingControllerTest() {
        super("/rating", Rating.class, Long.class);
    }

    @Override
    protected Rating getModelA() {
        return Rating.builder()
                .id(1L)
                .moodysRating("moodysRating")
                .sandPRating("sandPRating")
                .fitchRating("fitchRating")
                .orderNumber((byte) 2)
                .build();
    }

    @Override
    protected Rating getModelB() {
        return Rating.builder()
                .id(73582L)
                .moodysRating("pvxupSXofJ")
                .sandPRating("62EDwMlxVa")
                .fitchRating("SU2tecC93S")
                .orderNumber((byte) 58)
                .build();
    }
}
