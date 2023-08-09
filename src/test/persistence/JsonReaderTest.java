package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Game;
import model.Playing;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Citation:
// This class is modeled after the JsonReaderTest class in the
// JsonSerializationDemo starter file
class JsonReaderTest extends JsonTest {

    private Game game;

    @BeforeEach
    void runBefore() {
        game = new Game();
    }

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playing playing = reader.read(game);
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

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
//            Playing playing = reader.read(game);
//            checkPlayerEquals(playing);
//            checkEnemiesEquals(playing);
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }
}