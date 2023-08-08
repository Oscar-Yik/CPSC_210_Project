package model;

import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.PlayerConstants.IDLE;

/*
 * Represents the player character that the user controls
 */
public class Player extends Character {

    private static final int expThreshold = 200;
    private String name;
//    private String weapon;
    private int movementSpeed;
//    private int range;
    private int exp;
//    private String talent;
    private Enemy target;
    private Random rn;

    public Player() {

    }

    public Player(float x, float y) {
        super(x,y,144,144);
        this.strength = 50;
        levelUp();
        levelUp();
        currentHealth = maxHealth;
    }

    /*
     * EFFECTS: Constructs a player with a given name, weapon, movement speed
     * range, exp, damageMultiplier = 2, strength, and health
     */
    public Player(String playerName, String weapon, int strength, int health, int exp,
                  int level, String talent, int movementSpeed, int range) {
        name = playerName;
//        this.talent = talent;
//        this.weapon = weapon;
//        damageMultiplier = 2;
        this.exp = exp;
        for (int i = level; i > 0; i--) {
            levelUpValueOnly();
        }
        this.strength = strength;
        this.maxHealth = health;
        this.movementSpeed = movementSpeed;
//        this.range = range;
    }

    /*
     * REQUIRES: playerName has a non-zero length
     * EFFECTS: Constructs a player with a name, random talent, sword
     * weapon, damageMultiplier = 2, no exp, and 1 level
     */
    public Player(String playerName) {
        super();
        name = playerName;
        rn = new Random();
//        talent = receiveTalent(4);
//        weapon = "Sword";
//        damageMultiplier = 2;
        exp = 0;
        levelUp();
    }

    public String getName() {
        return this.name;
    }

    public int getMovementSpeed() {
        return this.movementSpeed;
    }

//    public int getRange() {
//        return this.range;
//    }

    public int getExp() {
        return this.exp;
    }

//    public String getWeapon() {
//        return this.weapon;
//    }
//
//    public String getTalent() {
//        return this.talent;
//    }

    public Enemy getTarget() {
        return this.target;
    }

    /*
     * MODIFIES: this
     * EFFECTS: returns talent which is randomly chosen as one of
     * Strength, Health Movement Speed, Range
     */
//    public String receiveTalent(int upperBound) {
//        switch (rn.nextInt(upperBound)) {
//            case 0:
//                this.talent = "Strength";
//                break;
//            case 1:
//                this.talent = "Health";
//                break;
//            case 2:
//                this.talent = "Movement Speed";
//                break;
//            case 3:
//                this.talent = "Range";
//                break;
//            default:
//                this.talent = "No Talent";
//        }
//        return talent;
//    }

    /*
     * REQUIRES: target is not null
     * MODIFIES: this
     * EFFECTS: sets player's target enemy
     */
    public void chooseTarget(Enemy target) {
        this.target = target;
    }

    /*
     * MODIFIES: this
     * EFFECTS: 100 exp is added to the player; if the player's
     * exp meets or exceeds the threshold, the player gains a level
     * and their exp is lowered by that threshold
     */
    public void addEXP() {
        this.exp += 100;
        if (exp >= expThreshold) {
            exp -= expThreshold;
            levelUp();
        }
    }

    /*
     * EFFECTS: returns a string representation of Player
     */
    @Override
    public String toString() {
        return "\tName = " + name + "\n\tStrength = " + strength + "\n\tHealth = " + maxHealth + ", "
                + "\n\tLevel = " + level.size() + ", \n\tExp = " + exp + "\n\tTalent = "
                //+ talent + "\n\tWeapon = " + weapon
                + "\n\tMovement Speed = " + movementSpeed;
                //+ "\n\tRange = " + range;
    }

    /*
     * EFFECTS: returns this player as a JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Level", this.level.size());
        json.put("Strength", this.strength);
        json.put("Health", this.maxHealth);
        json.put("Exp", this.exp);
//        json.put("Talent", this.talent);
//        json.put("Weapon", this.weapon);
        json.put("Movement Speed", this.movementSpeed);
//        json.put("Range", this.range);
        return json;
    }
}
