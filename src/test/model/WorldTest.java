package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Tests the World class
 */
public class WorldTest {

    private World testWorld;
    private World testEmptyWorld;
    private Player testPlayer;
    private Enemy testEnemy;

    @BeforeEach
    void runBefore() {
       testWorld = new World("testWorld");
       testEmptyWorld = new World();
       testPlayer = new Player("testPlayer");
       testEnemy = new Enemy();
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWorld.getMonsters().size());
        assertEquals("testWorld",testWorld.getPlayer().getName());
        assertEquals("Forest", testWorld.getEcosystem());
    }

    @Test
    void testNewConstructor() {
        assertEquals(0, testEmptyWorld.getMonsters().size());
        assertEquals("Forest", testEmptyWorld.getEcosystem());
    }

    @Test
    void testSetEcosystem() {
        assertEquals("Forest", testWorld.getEcosystem());
        assertEquals("Desert", testWorld.setEcosystem("Desert"));
    }

    @Test
    void testAddPlayer() {
        testWorld.addPlayer(testPlayer);
        assertEquals(testPlayer,testWorld.getPlayer());
    }

    @Test
    void testAddCertainMonsters() {
        testWorld.addMonsters(testEnemy);
        assertEquals(testEnemy,testWorld.getMonsters().get(0));
    }

    @Test
    void testAddMonsters() {
        testWorld.addMonsters();
        assertEquals(1, testWorld.getMonsters().size());

        testWorld.addMonsters();
        testWorld.addMonsters();
        assertEquals(3, testWorld.getMonsters().size());
    }

    @Test
    void testResetMonsters() {
        testWorld.resetMonsters();
        assertEquals(0, testWorld.getMonsters().size());

        testWorld.addMonsters();
        testWorld.resetMonsters();
        assertEquals(0, testWorld.getMonsters().size());

        testWorld.addMonsters();
        testWorld.addMonsters();
        testWorld.resetMonsters();
        assertEquals(0, testWorld.getMonsters().size());
    }

    @Test
    void testToString() {

        String enemies = "";
        int count = 1;
        for (Enemy monster : testWorld.getMonsters()) {
            enemies = enemies + "\tMonster " + count + ": " + monster.toString() + "\n";
            count++;
        }
        String testWorldString = "Player: \n" + testWorld.getPlayer() + ", \nEnemies: \n" + enemies
                + "Ecosystem = " + testWorld.getEcosystem();

        assertEquals(testWorldString,testWorld.toString());
    }

}