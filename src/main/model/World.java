package model;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Enemy> monsters;
    private Player player;
    private String ecosystem;

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
     * EFFECTS: adds a monster to the world
     */
    public void addMonsters() {
        monsters.add(new Enemy());
    }

    /*
     * EFFECTS: removes all monsters from list of monsters
     */
    public void resetMonsters() {
        monsters = new ArrayList<Enemy>();
    }

}
