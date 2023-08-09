package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Playing;
import model.Cavalier;
import org.json.*;
import model.EnemyHandler;
import model.Game;
import model.Player;

// Represents a reader that reads a playing object from JSON data stored in file
// Citation:
// This class is modeled after the JsonReader class in the JsonSerializationDemo starter file
// Link:
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads playing object from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Playing read(Game game) throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlaying(jsonObject, game);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playing from JSON object and returns it
    private Playing parsePlaying(JSONObject jsonObject, Game game) {
        Playing playing = new Playing(game);
        playing.setxLvlOffset(jsonObject.getInt("offsetX"));
        playing.setyLvlOffset(jsonObject.getInt("offsetY"));

        int numEnemies = jsonObject.getInt("numEnemies");
        game.setNumEnemies(numEnemies);

        addMonsters(playing, jsonObject, numEnemies);
        addPlayer(playing, jsonObject);
        playing.getEnemyHandler().setNumAlive(jsonObject.getInt("numAlive"));
        return playing;
    }

    // MODIFIES: playing
    // EFFECTS: parses playerUI from JSON object
    private void addPlayer(Playing playing, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("player");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addPlayerValues(playing, nextPlayer);
        }
    }

    // MODIFIES: playing, player
    // EFFECTS: parses playerUI values from JSON object and adds them to playing
    private void addPlayerValues(Playing playing, JSONObject jsonObject) {
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");
        Player player = new Player(x,y,playing);

        for (int i = 0; i < jsonObject.getInt("Level"); i++) {
            player.levelUpValueOnly();
        }

        player.setStrength(jsonObject.getInt("Strength"));
        player.setCurrentHealth(jsonObject.getInt("currentHealth"));
        player.setMaxHealth(jsonObject.getInt("maxHealth"));

        addPlayerHitbox(player, jsonObject);
        addPlayerAttackbox(player, jsonObject);
        addPlayerUI(player, jsonObject);

        playing.setPlayer(player);
    }

    // MODIFIES: player
    // EFFECTS: parses hitbox data from JSON object and adds them to player
    private void addPlayerHitbox(Player player, JSONObject jsonObject) {
        player.setHitboxX(jsonObject.getInt("hitboxX"));
        player.setHitboxY(jsonObject.getInt("hitboxY"));
        player.setHitboxWidth(jsonObject.getInt("hitboxW"));
        player.setHitboxHeight(jsonObject.getInt("hitboxH"));
    }

    // MODIFIES: player
    // EFFECTS: parses attackbox data from JSON object and adds them to player
    private void addPlayerAttackbox(Player player, JSONObject jsonObject) {
        player.setAttackBoxX(jsonObject.getInt("attackboxX"));
        player.setAttackBoxY(jsonObject.getInt("attackboxY"));
        player.setAttackBoxWidth(jsonObject.getInt("attackboxW"));
        player.setAttackBoxHeight(jsonObject.getInt("attackboxH"));
    }

    // MODIFIES: player
    // EFFECTS: parses UI data from JSON object and adds them to player
    private void addPlayerUI(Player player, JSONObject jsonObject) {
        player.setAniIndex(jsonObject.getInt("aniIndex"));
        player.setAniTick(jsonObject.getInt("aniTick"));
        player.setLeft(jsonObject.getBoolean("left"));
        player.setRight(jsonObject.getBoolean("right"));
        player.setUp(jsonObject.getBoolean("up"));
        player.setDown(jsonObject.getBoolean("down"));
        player.setFlipX(jsonObject.getInt("flipX"));
        player.setFlipW(jsonObject.getInt("flipW"));
    }

    // MODIFIES: playing, enemyHandler
    // EFFECTS: parses enemyHandler from JSON object and adds them to playing
    private void addMonsters(Playing playing, JSONObject jsonObject, int numEnemies) {
        JSONArray jsonArray = jsonObject.getJSONArray("enemies");
        EnemyHandler enemyHandler = new EnemyHandler(playing);
        for (Object json : jsonArray) {
            JSONObject nextMonster = (JSONObject) json;
            addMonster(enemyHandler, playing, nextMonster);
        }
        enemyHandler.setNumCavaliers(numEnemies);
        playing.setEnemyHandler(enemyHandler);
    }

    // MODIFIES: enemyHandler, cavalier
    // EFFECTS: parses cavalier from JSON object and adds it to enemyHandler
    private void addMonster(EnemyHandler enemyHandler, Playing playing, JSONObject jsonObject) {
        int x = jsonObject.getInt("x");
        int y = jsonObject.getInt("y");
        Cavalier cavalier = new Cavalier(x,y);

        for (int i = 0; i < jsonObject.getInt("Level"); i++) {
            cavalier.levelUpValueOnly();
        }

        cavalier.setStrength(jsonObject.getInt("Strength"));
        cavalier.setCurrentHealth(jsonObject.getInt("currentHealth"));
        cavalier.setMaxHealth(jsonObject.getInt("maxHealth"));

        addCavalierHitbox(cavalier, jsonObject);
        addCavalierAttackbox(cavalier, jsonObject);
        addCavalierUI(cavalier, jsonObject);

        enemyHandler.addCavalier(cavalier);
    }

    // MODIFIES: cavalier
    // EFFECTS: parses hitbox data from JSON object and adds them to cavalier
    private void addCavalierHitbox(Cavalier cavalier, JSONObject jsonObject) {
        cavalier.setHitboxX(jsonObject.getInt("hitboxX"));
        cavalier.setHitboxY(jsonObject.getInt("hitboxY"));
        cavalier.setHitboxWidth(jsonObject.getInt("hitboxW"));
        cavalier.setHitboxHeight(jsonObject.getInt("hitboxH"));
    }

    // MODIFIES: cavalier
    // EFFECTS: parses attackbox data from JSON object and adds them to cavalier
    private void addCavalierAttackbox(Cavalier cavalier, JSONObject jsonObject) {
        cavalier.setAttackBoxX(jsonObject.getInt("attackboxX"));
        cavalier.setAttackBoxY(jsonObject.getInt("attackboxY"));
        cavalier.setAttackBoxWidth(jsonObject.getInt("attackboxW"));
        cavalier.setAttackBoxHeight(jsonObject.getInt("attackboxH"));
    }

    // MODIFIES: cavalier
    // EFFECTS: parses UI data from JSON object and adds them to cavalier
    private void addCavalierUI(Cavalier cavalier, JSONObject jsonObject) {
        cavalier.setAniIndex(jsonObject.getInt("aniIndex"));
        cavalier.setAniTick(jsonObject.getInt("aniTick"));
        cavalier.setAlive(jsonObject.getBoolean("alive"));
        cavalier.setWalkDirX(jsonObject.getInt("walkDirX"));
        cavalier.setWalkDirY(jsonObject.getInt("walkDirY"));
        cavalier.setEnemyState(jsonObject.getInt("enemyState"));

    }
}
