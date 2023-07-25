package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents the game world where the player and enemies live/exist
 */
public class World implements Writable {
    private List<Enemy> monsters;
    private Player player;
    private String ecosystem;

    /*
     * EFFECTS: Constructs a world with no enemies, no player
     *          and a forest ecosystem
     */
    public World() {
        monsters = new ArrayList<Enemy>();
        ecosystem = "Forest";
    }

    /*
     * EFFECTS: Constructs a world with no enemies, a player with
     * a given name, and a forest ecosystem
     */
    public World(String playerName) {
        monsters = new ArrayList<Enemy>();
        player = new Player(playerName);
        ecosystem = "Forest";
    }

    public List<Enemy> getMonsters() {
        return this.monsters;
    }

    public Player getPlayer() {
        return this.player;
    }

    public String getEcosystem() {
        return this.ecosystem;
    }

    public String setEcosystem(String ecosystem) {
        this.ecosystem = ecosystem;
        return this.ecosystem;
    }

    /*
     * EFFECTS: adds a player to the world
     */
    public void addPlayer(Player player) {
        this.player = player;
    }

    /*
     * EFFECTS: adds a monster to the world
     */
    public void addMonsters() {
        monsters.add(new Enemy());
    }

    /*
     * EFFECTS: adds a given monster to the world
     */
    public void addMonsters(Enemy enemy) {
        monsters.add(enemy);
    }

    /*
     * EFFECTS: removes all monsters from list of monsters
     */
    public void resetMonsters() {
        monsters = new ArrayList<Enemy>();
    }


    /*
     * EFFECTS: returns a string representation of World
     */
    @Override
    public String toString() {
        String enemies = "";
        int count = 1;
        for (Enemy monster : monsters) {
            enemies = enemies + "\tMonster " + count + ": " + monster.toString() + "\n";
            count++;
        }
        String output = "Player: \n" + player + ", \nEnemies: \n" + enemies
                + "Ecosystem = " + ecosystem;
        return output;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player", playerToJson());
        json.put("enemies", enemiesToJson());
        json.put("ecosystem", this.ecosystem);
        return json;
    }

    // EFFECTS: returns player in this world as a JSON array
    private JSONArray playerToJson() {
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(player.toJson());
        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray enemiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Enemy e : monsters) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}
