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
    private Enemy testEnemy2;

    @BeforeEach
    void runBefore() {
        testEnemy = new Enemy();
        testEnemy2 = new Enemy(5,8);
        testPlayer = new Player();
    }

    @Test
    void testConstructor() {
        assertEquals(2,testEnemy.getLevel().size());
    }

    @Test
    void testNewConstructor() {
        assertEquals(0,testEnemy2.getLevel().size());
        assertEquals(5,testEnemy2.getStrength());
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
}
