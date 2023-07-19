package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {

    private World testWorld;

    @BeforeEach
    void runBefore() {
       testWorld = new World("testWorld");
    }

    @Test
    void testConstructor() {
        assertEquals(0, testWorld.getMonsters().size());
        assertEquals("testWorld",testWorld.getPlayer().getName());
        assertEquals("Forest", testWorld.getEcosystem());
    }

    @Test
    void testAddMonsters() {
        testWorld.addMonsters();
        assertEquals(1, testWorld.getMonsters().size());

        testWorld.addMonsters();
        testWorld.addMonsters();
        assertEquals(3, testWorld.getMonsters().size());
    }

}