package com.poseidon.app;

import com.poseidon.app.persistence.entity.TradeEntity;
import com.poseidon.app.persistence.repository.TradeRepository;
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
public class TradeTests {
    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void tradeTest() {
        TradeEntity trade = new TradeEntity("Trade Account", "Type");

        // Save
        trade = tradeRepository.save(trade);
        assertNotNull(trade.getId());
        assertEquals(trade.getAccount(), "Trade Account");

        // Update
        trade.setAccount("Trade Account Update");
        trade = tradeRepository.save(trade);
        assertEquals(trade.getAccount(), "Trade Account Update");

        // Find
        List<TradeEntity> listResult = tradeRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Long id = trade.getId();
        tradeRepository.delete(trade);
        Optional<TradeEntity> tradeList = tradeRepository.findById(id);
        assertFalse(tradeList.isPresent());
    }
}
