package model;

import java.util.Random;

/*
 * Represents the levels of the characters in the game
 */
public class Level {

    private int strengthPoints;
    private int healthPoints;
    private Random rn;

    /*
     * EFFECTS: Constructs a new level with random strength from 5 to 15
     * and health points from 10 to 30
     */
    public Level() {
        rn = new Random();
        strengthPoints = (rn.nextInt(3) + 1) * 5;
        healthPoints = (rn.nextInt(3) + 1) * 10;
    }

    public int getStrengthPoints() {
        return this.strengthPoints;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }
}
