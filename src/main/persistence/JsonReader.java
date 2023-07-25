package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Enemy;
import model.Player;
import model.World;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public World read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses world from JSON object and returns it
    private World parseWorkRoom(JSONObject jsonObject) {
        World w = new World();
        addMonsters(w, jsonObject);
        addPlayer(w, jsonObject);
        return w;
    }

    // MODIFIES: w
    // EFFECTS: parses player from JSON object and adds them to world
    private void addPlayer(World w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("player");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayerValues(w, nextPlayer);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses player from JSON object and adds them to world
    private void addPlayerValues(World w, JSONObject jsonObject) {
        int strength = jsonObject.getInt("Strength");
        int health = jsonObject.getInt("Health");
        String name = jsonObject.getString("Name");
        int lv = jsonObject.getInt("Level");
        int exp = jsonObject.getInt("Exp");
        String talent = jsonObject.getString("Talent");
        String weapon = jsonObject.getString("Weapon");
        int movementSpeed = jsonObject.getInt("Movement Speed");
        int range = jsonObject.getInt("Range");
        Player player = new Player(name, weapon, strength, health, exp, lv, talent, movementSpeed, range);
        w.addPlayer(player);
    }

    // MODIFIES: w
    // EFFECTS: parses enemies from JSON object and adds them to world
    private void addMonsters(World w, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("enemies");
        for (Object json : jsonArray) {
            JSONObject nextMonster = (JSONObject) json;
            addMonster(w, nextMonster);
        }
    }

    // MODIFIES: w
    // EFFECTS: parses Enemy from JSON object and adds it to World
    private void addMonster(World w, JSONObject jsonObject) {
        int strength = jsonObject.getInt("Strength");
        int health = jsonObject.getInt("Health");
        Enemy enemy = new Enemy(strength,health);
        int lv = jsonObject.getInt("Level");
        for (int i = lv; i > 0; i--) {
            enemy.levelUpValueOnly();
        }
        w.addMonsters(enemy);
    }
}
