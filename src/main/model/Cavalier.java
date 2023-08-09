package model;

import org.json.JSONObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static ui.Constants.EnemyConstants.*;

/*
 * Represents a Cavalier enemy that the player can defeat
 */
public class Cavalier extends Enemy {

    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;

    /*
     * EFFECTS: creates a cavalier with a given x and y
     */
    public Cavalier(float x, float y) {
        super(x, y, CAVALIER_WIDTH, CAVALIER_HEIGHT, CAVALIER);
        initHitbox(x,y,CAVALIER_WIDTH, CAVALIER_HEIGHT);
        initAttackbox();
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes the cavalier's attackbox
     */
    private void initAttackbox() {
        attackBox = new Rectangle2D.Float(posX - 20,posY,hitbox.width + 60,hitbox.height + 20);
        attackBoxOffsetX = 30;
    }

    public void setAttackBoxX(int attackBoxX) {
        this.attackBox.x = attackBoxX;
    }

    public void setAttackBoxY(int attackBoxY) {
        this.attackBox.y = attackBoxY;
    }

    public void setAttackBoxWidth(int attackBoxWidth) {
        this.attackBox.width = attackBoxWidth;
    }

    public void setAttackBoxHeight(int attackBoxHeight) {
        this.attackBox.height = attackBoxHeight;
    }

    /*
     * EFFECTS: draws the cavalier's hitbox
     */
    public void drawHitBox(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.PINK);
        g.drawRect((int)(hitbox.x - offsetX * 2.5), (int)(hitbox.y - offsetY * 2.5),
                (int)hitbox.width, (int)hitbox.height);
    }

    /*
     * EFFECTS: draws the cavalier's attackbox
     */
    public void drawAttackBox(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.RED);
        g.drawRect((int) (attackBox.x - offsetX * 2.5),(int) (attackBox.y - offsetY * 2.5),
                (int) attackBox.width,(int) attackBox.height);
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates cavalier's data
     */
    public void update(Player player, int offsetX, int offsetY) {
        updateMove(player, offsetX, offsetY);
        updateAnimationTick();
        updateAttackBox();
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates cavalier's attackbox
     */
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    /*
     * MODIFIES: this
     * EFFECTS: moves the cavalier
     */
    public void updateMove(Player player, int offsetX, int offsetY) {
        switch (enemyState) {
            case IDLE:
                newState(WALKING_W);
                break;
            case WALKING_E:
            case WALKING_W:
                checkRanges(player, offsetX, offsetY);
                moveX();
                moveY();
                break;
            case ATTACKING_W:
                updateAttack(player, offsetX, offsetY);
                break;
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if the cavalier is in attack or visual range
     *          and moves the cavalier if needed
     */
    private void checkRanges(Player player, int offsetX, int offsetY) {
        if (isPlayerInVisualRange(player, offsetX, offsetY)) {
            turnTowardsPlayer(player, offsetX, offsetY);
        }
        if (isPlayerInAttackRange(player, offsetX, offsetY)) {
            newState(ATTACKING_W);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: cavalier attacks the player at certain animation indexes
     */
    private void updateAttack(Player player, int offsetX, int offsetY) {
        if (getEnemyIndex() == 0) {
            attackChecked = false;
        }
        if (getEnemyIndex() == 2 && !attackChecked) {
            checkEnemyHit(attackBox, player, offsetX, offsetY);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates health bar
     */
    @Override
    public void updateHealthBar() {
        super.updateHealthBar();
    }

    /*
     * EFFECTS: draws health bar
     */
    @Override
    public void drawUI(Graphics g, int offsetX, int offsetY) {
        super.drawUI(g, offsetX, offsetY);
    }

    /*
     * EFFECTS: returns cavalier as a JSONObject
     */
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Level", this.level.size());
        json.put("Strength", this.strength);
        json.put("maxHealth", this.maxHealth);
        json.put("currentHealth", this.currentHealth);
        toJsonHitbox(json);
        toJsonAttackbox(json);
        json.put("x", posX);
        json.put("y", posY);
        toJsonUI(json);
        return json;
    }

    /*
     * EFFECTS: parses cavalier's hitbox data into JSONObject
     */
    private void toJsonHitbox(JSONObject json) {
        json.put("hitboxX", hitbox.x);
        json.put("hitboxY", hitbox.y);
        json.put("hitboxW", hitbox.width);
        json.put("hitboxH", hitbox.height);
    }

    /*
     * EFFECTS: parses cavalier's attackbox data into JSONObject
     */
    private void toJsonAttackbox(JSONObject json) {
        json.put("attackboxX", attackBox.x);
        json.put("attackboxY", attackBox.y);
        json.put("attackboxW", attackBox.width);
        json.put("attackboxH", attackBox.height);
    }

    /*
     * EFFECTS: parses cavalier's UI data into JSONObject
     */
    private void toJsonUI(JSONObject json) {
        json.put("enemyState", enemyState);
        json.put("aniTick", aniTick);
        json.put("aniIndex", aniIndex);
        json.put("walkDirX", walkDirX);
        json.put("walkDirY", walkDirY);
        json.put("alive", alive);
    }

    /*
     * EFFECTS: returns a string representation of a Cavalier
     */
    @Override
    public String toString() {
        return "Strength: " + getStrength() + " EnemyState: " + getEnemyState() + " MaxHealth: " + maxHealth
                + " aniIndex: " + aniIndex + " HitboxX: " + hitbox.x + " CurrentHealth: " + currentHealth
                + " alive: " + alive;
    }


}
