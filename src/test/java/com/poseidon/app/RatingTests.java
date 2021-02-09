package com.poseidon.app;

import com.poseidon.app.persistence.entity.RatingEntity;
import com.poseidon.app.persistence.repository.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RatingTests {
    @Autowired
    private RatingRepository ratingRepository;

    @Test
    public void ratingTest() {
        RatingEntity rating = new RatingEntity("Moodys Rating", "Sand PRating", "Fitch Rating", (byte) 10);

        // Save
        rating = ratingRepository.save(rating);
        assertNotNull(rating.getId());
        assertEquals(rating.getOrderNumber(), (byte) 10);

        // Update
        rating.setOrderNumber((byte) 20);
        rating = ratingRepository.save(rating);
        assertEquals(rating.getOrderNumber(), (byte) 20);

        // Find
        List<RatingEntity> listResult = ratingRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Long id = rating.getId();
        ratingRepository.delete(rating);
        Optional<RatingEntity> ratingList = ratingRepository.findById(id);
        assertFalse(ratingList.isPresent());
    }
}
