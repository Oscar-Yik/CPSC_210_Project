package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests the Enemy class
 */
public class EnemyTest {

    private Enemy testEnemy;
    private Player testPlayer;

    @BeforeEach
    void runBefore() {
        testEnemy = new Enemy();
        testPlayer = new Player("testPlayer");
    }

    @Test
    void testConstructor() {
        assertEquals(2, testEnemy.getDamageMultiplier());
        assertEquals(2,testEnemy.getLevel().size());
    }

    @Test
    void testPlayerScale() {
        // player level is less than enemy level
        testEnemy.characterScale(testPlayer);
        assertEquals(2,testEnemy.getLevel().size());

        // player level is exactly the same as enemy level
        testPlayer.levelUp();
        testEnemy.characterScale(testPlayer);
        assertEquals(7,testEnemy.getLevel().size());

        // player level is much more than enemy level
        for (int i = 0; i < 10; i++) {
            testPlayer.levelUp();
        }
        testEnemy.characterScale(testPlayer);
        assertEquals(12,testEnemy.getLevel().size());
    }

    @Test
    void testToString() {
        assertEquals("[ Strength = " + testEnemy.getStrength() + ", Health = " + testEnemy.getHealth() + ", "
                + "Level = " + testEnemy.getLevel().size() + "]",testEnemy.toString());
    }
}
