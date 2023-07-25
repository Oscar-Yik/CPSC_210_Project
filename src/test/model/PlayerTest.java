package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests the Player class
 */
public class PlayerTest {

    private Player testPlayer;
    private Player testPlayer2;
    private Enemy testEnemy;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("testPlayer");
        testPlayer2 = new Player("Hero", "Sword", 78, 34, 0,
        6, "Health", 0, 0);
        testEnemy = new Enemy();
    }

    @Test
    void testConstructor() {
        assertEquals("testPlayer", testPlayer.getName());
        assertEquals("Sword", testPlayer.getWeapon());
        assertEquals(2, testPlayer.getDamageMultiplier());
        assertEquals(0, testPlayer.getExp());
        assertEquals(1, testPlayer.getLevel().size());
        assertTrue(testTalent(testPlayer.getTalent()));
    }

    @Test
    void testNewConstructor() {
        assertEquals("Hero", testPlayer2.getName());
        assertEquals("Sword", testPlayer2.getWeapon());
        assertEquals(2, testPlayer2.getDamageMultiplier());
        assertEquals(0, testPlayer2.getExp());
        assertEquals(6, testPlayer2.getLevel().size());
        assertEquals(78, testPlayer2.getStrength());
        assertEquals(34, testPlayer2.getHealth());
        assertEquals(0, testPlayer2.getMovementSpeed());
        assertEquals(0, testPlayer2.getRange());
        assertTrue(testTalent(testPlayer2.getTalent()));
    }

    @Test
    void testReceiveTalent() {
        assertTrue(testTalent(testPlayer.receiveTalent()));
    }

    public boolean testTalent(String talent) {
        return talent == "Strength" || talent == "Health" || talent == "Movement Speed" || talent == "Range";
    }

    @Test
    void testChooseTarget() {
        testPlayer.chooseTarget(testEnemy);
        assertEquals(testEnemy, testPlayer.getTarget());
    }

    @Test
    void testAddEXP() {
        testPlayer.addEXP();
        assertEquals(100,testPlayer.getExp());
        testPlayer.addEXP();
        assertEquals(0,testPlayer.getExp());
        assertEquals(2,testPlayer.getLevel().size());
    }


}