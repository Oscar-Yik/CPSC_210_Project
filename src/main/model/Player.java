package model;

import org.json.JSONObject;

import java.util.Random;

/*
 * Represents the player character that the user controls
 */
public class Player extends Character {

    private static final int expThreshold = 200;
    private String name;
    private String weapon;
    private int movementSpeed;
    private int range;
    private int exp;
    private String talent;
    private Enemy target;
    private Random rn;

    /*
     * EFFECTS: Constructs a player with a given name, weapon, movement speed
     * range, exp, damageMultiplier = 2, strength, and health
     */
    public Player(String playerName, String weapon, int strength, int health, int exp,
                  int level, String talent, int movementSpeed, int range) {
        name = playerName;
        this.talent = talent;
        this.weapon = weapon;
        damageMultiplier = 2;
        this.exp = exp;
        for (int i = level; i > 0; i--) {
            levelUpValueOnly();
        }
        this.strength = strength;
        this.health = health;
        this.movementSpeed = movementSpeed;
        this.range = range;
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
        talent = receiveTalent();
        weapon = "Sword";
        damageMultiplier = 2;
        exp = 0;
        levelUp();
    }

    public String getName() {
        return this.name;
    }

    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    public int getRange() {
        return this.range;
    }

    public int getExp() {
        return this.exp;
    }

    public String getWeapon() {
        return this.weapon;
    }

    public String getTalent() {
        return this.talent;
    }

    public Enemy getTarget() {
        return this.target;
    }

    /*
     * MODIFIES: this
     * EFFECTS: returns talent which is randomly chosen as one of
     * Strength, Health Movement Speed, Range
     */
    public String receiveTalent() {
        switch (rn.nextInt(4)) {
            case 0:
                this.talent = "Strength";
                break;
            case 1:
                this.talent = "Health";
                break;
            case 2:
                this.talent = "Movement Speed";
                break;
            case 3:
                this.talent = "Range";
                break;
        }
        return talent;
    }

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
        return "\tName = " + name + "\n\tStrength = " + strength + "\n\tHealth = " + health + ", "
                + "\n\tLevel = " + level.size() + ", \n\tExp = " + exp + "\n\tTalent = " + talent
                + "\n\tWeapon = " + weapon + "\n\tMovement Speed = " + movementSpeed
                + "\n\tRange = " + range;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Level", this.level.size());
        json.put("Strength", this.strength);
        json.put("Health", this.health);
        json.put("Exp", this.exp);
        json.put("Talent", this.talent);
        json.put("Weapon", this.weapon);
        json.put("Movement Speed", this.movementSpeed);
        json.put("Range", this.range);
        return json;
    }
}
