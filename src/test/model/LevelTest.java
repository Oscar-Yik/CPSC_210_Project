package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests the Level class
 */
public class LevelTest {

    private Level testLevel;

    @BeforeEach
    void runBefore() {
        testLevel = new Level();
    }

    public boolean strengthWithinBounds() {
        return testLevel.getStrengthPoints() < 4 && testLevel.getStrengthPoints() > 0;
    }

    public boolean healthWithinBounds() {
        return testLevel.getHealthPoints() < 4 && testLevel.getHealthPoints() > 0;
    }

    @Test
    void testConstructor() {
        assertTrue(strengthWithinBounds());
        assertTrue(healthWithinBounds());
    }

}