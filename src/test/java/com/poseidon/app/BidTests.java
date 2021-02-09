package com.poseidon.app;

import com.poseidon.app.persistence.entity.BidListEntity;
import com.poseidon.app.persistence.repository.BidListRepository;
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
public class BidTests {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    public void bidListTest() {
        BidListEntity bid = new BidListEntity("Account Test", "Type Test", 10d);

        // Save
        bid = bidListRepository.save(bid);
        assertNotNull(bid.getId());
        assertEquals(bid.getBidQuantity(), 10d, 10d);

        // Update
        bid.setBidQuantity(20d);
        bid = bidListRepository.save(bid);
        assertEquals(bid.getBidQuantity(), 20d, 20d);

        // Find
        List<BidListEntity> listResult = bidListRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Long id = bid.getId();
        bidListRepository.delete(bid);
        Optional<BidListEntity> bidList = bidListRepository.findById(id);
        assertFalse(bidList.isPresent());
    }
}
