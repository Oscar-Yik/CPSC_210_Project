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
        assertEquals(0, testPlayer.getExp());
        assertEquals(1, testPlayer.getLevel().size());
    }

    @Test
    void testNewConstructor() {
        assertEquals("Hero", testPlayer2.getName());
        assertEquals(0, testPlayer2.getExp());
        assertEquals(6, testPlayer2.getLevel().size());
        assertEquals(78, testPlayer2.getStrength());
        assertEquals(0, testPlayer2.getMovementSpeed());
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

//    @Test
//    void testToString() {
//        String testPlayerString = "\tName = " + testPlayer.getName() + "\n\tStrength = "
//                + testPlayer.getStrength() + "\n\tHealth = " + testPlayer.getHealth() + ", "
//                + "\n\tLevel = " + testPlayer.getLevel().size() + ", \n\tExp = " + testPlayer.getExp()
//                + "\n\tTalent = " + testPlayer.getTalent() + "\n\tWeapon = " + testPlayer.getWeapon()
//                + "\n\tMovement Speed = " + testPlayer.getMovementSpeed() + "\n\tRange = " + testPlayer.getRange();
//        String testPlayer2String = "\tName = " + testPlayer2.getName() + "\n\tStrength = "
//                + testPlayer2.getStrength() + "\n\tHealth = " + testPlayer2.getHealth() + ", "
//                + "\n\tLevel = " + testPlayer2.getLevel().size() + ", \n\tExp = " + testPlayer2.getExp()
//                + "\n\tTalent = " + testPlayer2.getTalent() + "\n\tWeapon = " + testPlayer2.getWeapon()
//                + "\n\tMovement Speed = " + testPlayer2.getMovementSpeed() + "\n\tRange = " + testPlayer2.getRange();
//        assertEquals(testPlayerString,testPlayer.toString());
//        assertEquals(testPlayer2String,testPlayer2.toString());
//    }


}