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

//    @Test
//    void testLevelUp() {
//        int oldStr = testCharacter.getStrength();
//        testCharacter.levelUp();
//        int newStr = testCharacter.getStrength();
//        assertEquals(1,testCharacter.getLevel().size());
//        assertTrue(statsWithinBounds(oldStr,newStr));
//    }

    @Test
    void testLevelUpValueOnly() {
        int oldStr = testCharacter.getStrength();
        testCharacter.levelUpValueOnly();
        assertEquals(1,testCharacter.getLevel().size());
        assertEquals(oldStr,testCharacter.getStrength());
    }

    public boolean statsWithinBounds(int oldStat, int newStat) {
        return newStat < oldStat + 4 && newStat > oldStat;
    }

}
