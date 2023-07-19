package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player testPlayer;
    private Enemy testEnemy;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("testPlayer");
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