package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests the Character class
 */
public class CharacterTest {

    private Character testCharacter;
    private Character testCharacter2;

    @BeforeEach
    void runBefore() {
        testCharacter = new Character();
        testCharacter2 = new Character();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testCharacter.getLevel().size());
    }

    @Test
    void testTakeDamage() {
        testCharacter.setDamageMultiplier(1);

        testCharacter.takeDamage(20);
        assertEquals(0,testCharacter.getHealth());

        testCharacter.setHealth(4);
        testCharacter.takeDamage(2);
        assertEquals(2,testCharacter.getHealth());
        testCharacter.takeDamage(2);
        assertEquals(0,testCharacter.getHealth());

        testCharacter.setHealth(4);
        testCharacter.takeDamage(6);
        assertEquals(0,testCharacter.getHealth());
    }

    @Test
    void testLevelUp() {
        int oldStr = testCharacter.getStrength();
        int oldHel = testCharacter.getHealth();
        testCharacter.levelUp();
        int newStr = testCharacter.getStrength();
        int newHel = testCharacter.getHealth();
        assertEquals(1,testCharacter.getLevel().size());
        assertTrue(statsWithinBounds(oldStr,newStr));
        assertTrue(statsWithinBounds(oldHel,newHel));
    }

    @Test
    void testLevelUpValueOnly() {
        int oldStr = testCharacter.getStrength();
        int oldHel = testCharacter.getHealth();
        testCharacter.levelUpValueOnly();
        assertEquals(1,testCharacter.getLevel().size());
        assertEquals(oldStr,testCharacter.getStrength());
        assertEquals(oldHel,testCharacter.getHealth());
    }

    public boolean statsWithinBounds(int oldStat, int newStat) {
        return newStat < oldStat + 4 && newStat > oldStat;
    }

    @Test
    void testGetDamage() {
        testCharacter.setStrength(1);
        testCharacter.setDamageMultiplier(1);
        assertEquals(1,testCharacter.getDamage());

        testCharacter.setStrength(0);
        testCharacter.setDamageMultiplier(1);
        assertEquals(0,testCharacter.getDamage());

        testCharacter.setStrength(1);
        testCharacter.setDamageMultiplier(0);
        assertEquals(0,testCharacter.getDamage());

        testCharacter.setStrength(0);
        testCharacter.setDamageMultiplier(0);
        assertEquals(0,testCharacter.getDamage());

        testCharacter.setStrength(10);
        testCharacter.setDamageMultiplier(62);
        assertEquals(620,testCharacter.getDamage());
    }

}
