package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests the Player class
 */
public class PlayerTest {

    private Player testPlayer;
    private Player testPlayer2;
    private Player testPlayerTalent1;
    private Player testPlayerTalent2;
    private Player testPlayerTalent3;
    private Player testPlayerTalent4;
    private Player testPlayerTalent5;
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
        testPlayerTalent1 = new Player("hero");
        testPlayerTalent2 = new Player("hero");
        testPlayerTalent3 = new Player("hero");
        testPlayerTalent4 = new Player("hero");
        testPlayerTalent5 = new Player("hero");
        assertTrue(testTalent(testPlayerTalent1.receiveTalent(4)));
        assertTrue(testTalent(testPlayerTalent2.receiveTalent(4)));
        assertTrue(testTalent(testPlayerTalent3.receiveTalent(4)));
        assertTrue(testTalent(testPlayerTalent4.receiveTalent(4)));
        assertFalse(testTalent(testPlayerTalent5.receiveTalent(800)));
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

    @Test
    void testToString() {
        String testPlayerString = "\tName = " + testPlayer.getName() + "\n\tStrength = "
                + testPlayer.getStrength() + "\n\tHealth = " + testPlayer.getHealth() + ", "
                + "\n\tLevel = " + testPlayer.getLevel().size() + ", \n\tExp = " + testPlayer.getExp()
                + "\n\tTalent = " + testPlayer.getTalent() + "\n\tWeapon = " + testPlayer.getWeapon()
                + "\n\tMovement Speed = " + testPlayer.getMovementSpeed() + "\n\tRange = " + testPlayer.getRange();
        String testPlayer2String = "\tName = " + testPlayer2.getName() + "\n\tStrength = "
                + testPlayer2.getStrength() + "\n\tHealth = " + testPlayer2.getHealth() + ", "
                + "\n\tLevel = " + testPlayer2.getLevel().size() + ", \n\tExp = " + testPlayer2.getExp()
                + "\n\tTalent = " + testPlayer2.getTalent() + "\n\tWeapon = " + testPlayer2.getWeapon()
                + "\n\tMovement Speed = " + testPlayer2.getMovementSpeed() + "\n\tRange = " + testPlayer2.getRange();
        assertEquals(testPlayerString,testPlayer.toString());
        assertEquals(testPlayer2String,testPlayer2.toString());
    }


}