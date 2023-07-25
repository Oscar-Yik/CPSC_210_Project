package persistence;

import model.Player;
import model.World;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            World w = new World();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            World emptyWorld = new World("Hero");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorld.json");

            writer.open();
            writer.write(emptyWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorld.json");
            emptyWorld = reader.read();
            checkPlayerEquals(emptyWorld);
            assertEquals("Forest", emptyWorld.getEcosystem());
            assertEquals(0, emptyWorld.getMonsters().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            World generalWorld = new World("Hero");
            generalWorld.addMonsters();
            generalWorld.addMonsters();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(generalWorld);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            generalWorld = reader.read();

            checkPlayerEquals(generalWorld);
            checkEnemyEquals(generalWorld);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}