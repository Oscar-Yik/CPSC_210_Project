package model;

/*
 * Represents an enemy that the player can defeat in the game
 */
public class Enemy extends Character {

    /*
     * EFFECTS: Constructs an Enemy object with damageMultiplier = 1
     * and 2 levels
     */
    public Enemy() {
        super();
        damageMultiplier = 2;
        levelUp();
        levelUp();
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
