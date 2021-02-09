package com.poseidon.app;

import com.poseidon.app.persistence.entity.CurvePointEntity;
import com.poseidon.app.persistence.repository.CurvePointRepository;
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
public class CurvePointTests {

    @Autowired
    private CurvePointRepository curvePointRepository;

    @Test
    public void curvePointTest() {
        CurvePointEntity curvePoint = new CurvePointEntity(10L, 10d, 30d);

        // Save
        curvePoint = curvePointRepository.save(curvePoint);
        assertNotNull(curvePoint.getId());
        assertEquals(curvePoint.getCurveId(), 10L);

        // Update
        curvePoint.setCurveId(20L);
        curvePoint = curvePointRepository.save(curvePoint);
        assertEquals(curvePoint.getCurveId(), 20L);

        // Find
        List<CurvePointEntity> listResult = curvePointRepository.findAll();
        assertTrue(listResult.size() > 0);

        // Delete
        Long id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePointEntity> curvePointList = curvePointRepository.findById(id);
        assertFalse(curvePointList.isPresent());
    }

}
