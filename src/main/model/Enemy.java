package model;

import java.util.List;

/*
 * Represents an enemy that the player can defeat in the game
 */
public class Enemy extends Character {

    /*
     * EFFECTS: Constructs an Enemy object with damageMultiplier = 2
     * and 2 levels
     */
    public Enemy() {
        super();
        damageMultiplier = 2;
        levelUp();
        levelUp();
    }

    /*
     * EFFECTS: Constructs an Enemy object with damageMultiplier = 2,
     * a given strength and health
     */
    public Enemy(int strength, int health) {
        super();
        this.strength = strength;
        this.health = health;
        damageMultiplier = 2;
    }

    /*
     * REQUIRES: Player is not null
     * MODIFIES: this
     * EFFECTS: If the player's level is greater than or equal to
     * the enemy's level, add 5 levels to the enemy
     */
    public void characterScale(Player player) {
        if (player.getLevel().size() >= this.getLevel().size()) {
            for (int i = 0; i < 5; i++) {
                this.levelUp();
            }
        }
    }

    /*
     * EFFECTS: returns a string representation of Enemy
     */
    @Override
    public String toString() {
        return "[ Strength = " + strength + ", Health = " + health + ", "
                + "Level = " + level.size() + "]";
    }

}
