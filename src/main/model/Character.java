package model;

import java.util.ArrayList;
import java.util.List;

/*
 * Represents the characters in the game such as players and enemies
 */
public class Character {

    protected int strength;
    protected int health;
    protected List<Level> level;
    protected int damageMultiplier;

    /*
     * EFFECTS: Constructs a new character with no levels
     */
    public Character() {
        level = new ArrayList<>();
    }

    public int getStrength() {
        return this.strength;
    }

    public int getHealth() {
        return this.health;
    }

    public List<Level> getLevel() {
        return this.level;
    }

    public int getDamageMultiplier() {
        return this.damageMultiplier;
    }

    public int setStrength(int str) {
        this.strength = str;
        return this.strength;
    }

    public int setHealth(int health) {
        this.health = health;
        return this.health;
    }

    public int setDamageMultiplier(int dm) {
        this.damageMultiplier = dm;
        return this.damageMultiplier;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Character loses health equal to the strength times its
     * damageMultiplier only up to
     */
    public void takeDamage(int strength) {
        if (this.health > 0) {
            this.health -= strength * this.damageMultiplier;
            if (this.health < 0) {
                this.health = 0;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a level to the character and its
     * respective stat points from the level
     */
    public void levelUp() {
        Level lv = new Level();
        strength += lv.getStrengthPoints();
        health += lv.getHealthPoints();
        level.add(lv);
    }

    /*
     * EFFECTS: returns damage taken from an attack
     */
    public int getDamage() {
        return this.strength * this.damageMultiplier;
    }
}
