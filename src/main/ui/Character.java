package ui;

import model.Level;
import persistence.Writable;

import org.json.JSONObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/*
 * Represents the characters in the game such as players and enemies
 */
public class Character implements Writable {

    protected int strength = 10;
    protected List<Level> level;
    protected float posX;
    protected float posY;
    protected int width;
    protected int height;
    protected Rectangle2D.Float hitbox;

    // Status Bar UI
    private BufferedImage statusBarImg;
    private BufferedImage statusBarSubImage;
    private BufferedImage healthImg;
    private BufferedImage healthSubImage;
    private int statusBarWidth = 135;
    private int statusBarHeight = 29;

    private int healthBarWidth = 125;
    private int healthBarHeight = 10;

    protected int maxHealth = 100;
    protected int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    /*
     * EFFECTS: Constructs a new character with given position
     */
    public Character(float x, float y, int width, int height) {
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;

        statusBarImg = LoadImages.getSpriteAtlas(LoadImages.HEALTH_BAR);
        healthImg = LoadImages.getSpriteAtlas(LoadImages.HEALTH);

        level = new ArrayList<>();
    }

    /*
     * EFFECTS: Constructs a new character with no levels
     */
    public Character() {
        level = new ArrayList<>();
    }

    public float getX() {
        return this.posX;
    }

    public float getY() {
        return this.posY;
    }

    public int getStrength() {
        return this.strength;
    }

    public List<Level> getLevel() {
        return this.level;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setStrength(int str) {
        this.strength = str;
    }

    public void setCurrentHealth() {
        this.currentHealth = maxHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setHitboxX(float x) {
        this.hitbox.x = x;
    }

    public void setHitboxY(float y) {
        this.hitbox.y = y;
    }

    public void setHitboxWidth(float width) {
        this.hitbox.width = width;
    }

    public void setHitboxHeight(float height) {
        this.hitbox.height = height;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /*
     * EFFECTS: Shows the hitbox for debugging
     */
    protected void drawHitBox(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.PINK);
        g.drawRect((int)hitbox.x - offsetX, (int)hitbox.y - offsetY, (int)hitbox.width, (int)hitbox.height);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Initialize character's hit box
     */
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return this.hitbox;
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates character's health bar
     */
    protected void updateHealthBar() {
        float healthRatio = (currentHealth / (float) maxHealth);
        healthWidth = (int) (healthRatio * healthBarWidth);
        if (healthRatio <= 0) {
            healthRatio = 0;
        }
        healthSubImage = healthImg.getSubimage(11,18, (int) (332 * healthRatio) + 1, healthBarHeight);
    }

    /*
     * EFFECTS: draws character's health bar
     */
    protected void drawUI(Graphics g, int offsetX, int offsetY) {
        statusBarSubImage = statusBarImg.getSubimage(14,20, 351, 49);

        g.drawImage(statusBarSubImage,(int) hitbox.x - offsetX, (int) hitbox.y - 25 - offsetY,
                statusBarWidth,statusBarHeight, null);
        g.drawImage(healthSubImage, (int) hitbox.x + 4 - offsetX, (int) hitbox.y - 17 - offsetY,
                healthWidth,healthBarHeight,null);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds health to character's health bar
     */
    public void changeHealth(int strength) {
        currentHealth += strength;

        if (currentHealth <= 0) {
            currentHealth = 0;
        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public int getCurrentHealth() {
        return this.currentHealth;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a level to the character and its
     * respective stat points from the level
     */
    public void levelUp() {
        Level lv = new Level();
        strength += lv.getStrengthPoints();
        maxHealth += lv.getHealthPoints();
        level.add(lv);
    }

    /*
     * MODIFIES: this
     * EFFECTS: only adds a level to the character without
     *          extra stat points for the level
     */
    public void levelUpValueOnly() {
        Level lv = new Level();
        level.add(lv);
    }

    /*
     * EFFECTS: returns this character as a JSONObject
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
