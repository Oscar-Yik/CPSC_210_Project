package persistence;

import model.Enemy;
import model.Player;
import model.World;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
//    protected void checkThingy(String name, Category category, Thingy thingy) {
//        assertEquals(name, thingy.getName());
//        assertEquals(category, thingy.getCategory());
//    }
    protected void checkPlayerEquals(World w) {
        checkStatRange(w.getPlayer().getLevel().size(),w.getPlayer().getHealth());
        checkStatRange(w.getPlayer().getLevel().size(),w.getPlayer().getStrength());

        assertEquals(0, w.getPlayer().getExp());
        assertEquals(0, w.getPlayer().getRange());
        assertEquals(0, w.getPlayer().getMovementSpeed());
        assertEquals("Sword", w.getPlayer().getWeapon());
        assertEquals("Hero", w.getPlayer().getName());
        assertEquals(1, w.getPlayer().getLevel().size());
        assertEquals("Range", w.getPlayer().getTalent());
    }

    private boolean testTalent(String talent) {
        return talent == "Strength" || talent == "Health" || talent == "Movement Speed" || talent == "Range";
    }

    protected void checkStatRange(int lv, int stat) {
        int lo = 1*lv;
        int hi = 3*lv;
        assertTrue(stat <= hi && stat >= lo);
    }

    protected void checkEnemyEquals(World w) {
        for (Enemy enemy: w.getMonsters()) {
            assertEquals(2, enemy.getDamageMultiplier());
            assertEquals(2,enemy.getLevel().size());
            checkStatRange(2,enemy.getHealth());
            checkStatRange(2,enemy.getStrength());
        }
    }
}
