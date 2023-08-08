package model;

import org.json.JSONObject;
import ui.Character;

import java.util.Random;

/*
 * Represents the player character that the user controls
 */
public class Player extends Character {

    private Random rn;

    /*
     * EFFECTS: Constructs a player with a given position, 50 strength,
     *          2 levels, and currentHealth = maxHealth
     */
    public Player(float x, float y) {
        super(x,y,144,144);
        this.strength = 50;
        levelUp();
        levelUp();
        currentHealth = maxHealth;
    }

    /*
     * EFFECTS: Constructs a player with a given strength, maxHealth, and level
     */
    public Player(int strength, int health, int level) {
        for (int i = level; i > 0; i--) {
            levelUpValueOnly();
        }
        this.strength = strength;
        this.maxHealth = health;
    }

    /*
     * EFFECTS: Constructs a player with a level
     */
    public Player() {
        super();
        rn = new Random();
        levelUp();
    }

    /*
     * EFFECTS: returns a string representation of Player
     */
    @Override
    public String toString() {
        return "\n\tStrength = " + strength + "\n\tHealth = " + maxHealth + ", "
                + "\n\tLevel = " + level.size();
    }

    /*
     * EFFECTS: returns this player as a JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Level", this.level.size());
        json.put("Strength", this.strength);
        json.put("Health", this.maxHealth);
        return json;
    }
}
