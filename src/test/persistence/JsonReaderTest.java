package persistence;

import model.Enemy;
import model.Player;
import model.World;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Citation(?):
// This class is modeled after the JsonReaderTest class in the
// JsonSerializationDemo starter file
class JsonReaderTest extends JsonTest {

//
//    @Test
//    void testReaderNonExistentFile() {
//        JsonReader reader = new JsonReader("./data/noSuchFile.json");
//        try {
//            World w = reader.read();
//            fail("IOException expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    void testReaderEmptyWorld() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyWorld.json");
//        try {
//            World w = reader.read();
//            assertEquals("Forest", w.getEcosystem());
//            assertEquals(0, w.getMonsters().size());
//            checkPlayerEquals(w);
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
//
//    @Test
//    void testReaderGeneralWorld() {
//        JsonReader reader = new JsonReader("./data/testReaderGeneralWorld.json");
//        try {
//            World w = reader.read();
//            Player testPlayer = new Player("Hero", "Sword",
//                    3, 1, 0, 1, "Range",
//                    0, 0);
//            checkPlayerEquals(w);
//            checkEnemyEquals(w);
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}