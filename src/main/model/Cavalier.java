package model;

import org.json.JSONObject;
import ui.EnemyUI;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;

public class Cavalier extends EnemyUI {

    private Rectangle2D.Float attackBox;
    private int attackBoxOffsetX;
    //private boolean alive = true;

    public Cavalier(float x, float y) {
        super(x, y, CAVALIER_WIDTH, CAVALIER_HEIGHT, CAVALIER);
        initHitbox(x,y,CAVALIER_WIDTH, CAVALIER_HEIGHT);
        initAttackbox();
    }

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


    public void drawHitBox(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.PINK);
        g.drawRect((int)(hitbox.x - offsetX * 2.5), (int)(hitbox.y - offsetY * 2.5),
                (int)hitbox.width, (int)hitbox.height);
    }

    public void drawAttackBox(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.RED);
        g.drawRect((int) (attackBox.x - offsetX * 2.5),(int) (attackBox.y - offsetY * 2.5),
                (int) attackBox.width,(int) attackBox.height);
    }

    public void update(Player player, int offsetX, int offsetY) {
        updateMove(player, offsetX, offsetY);
        updateAnimationTick();
        updateAttackBox();
    }

    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

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

    private void checkRanges(Player player, int offsetX, int offsetY) {
        if (isPlayerInVisualRange(player, offsetX, offsetY)) {
            turnTowardsPlayer(player, offsetX, offsetY);
        }
        if (isPlayerInAttackRange(player, offsetX, offsetY)) {
            newState(ATTACKING_W);
        }
    }

    private void updateAttack(Player player, int offsetX, int offsetY) {
        if (getEnemyIndex() == 0) {
            attackChecked = false;
        }
        if (getEnemyIndex() == 2 && !attackChecked) {
            checkEnemyHit(attackBox, player, offsetX, offsetY);
        }
    }

    @Override
    public void updateHealthBar() {
        super.updateHealthBar();
    }

    @Override
    public void drawUI(Graphics g, int offsetX, int offsetY) {
        super.drawUI(g, offsetX, offsetY);
    }

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

    private void toJsonHitbox(JSONObject json) {
        json.put("hitboxX", hitbox.x);
        json.put("hitboxY", hitbox.y);
        json.put("hitboxW", hitbox.width);
        json.put("hitboxH", hitbox.height);
    }

    private void toJsonAttackbox(JSONObject json) {
        json.put("attackboxX", attackBox.x);
        json.put("attackboxY", attackBox.y);
        json.put("attackboxW", attackBox.width);
        json.put("attackboxH", attackBox.height);
    }

    private void toJsonUI(JSONObject json) {
        json.put("enemyState", enemyState);
        json.put("aniTick", aniTick);
        json.put("aniIndex", aniIndex);
        json.put("walkDirX", walkDirX);
        json.put("walkDirY", walkDirY);
        json.put("alive", alive);
    }

    @Override
    public String toString() {
        return "Strength: " + getStrength() + " EnemyState: " + getEnemyState() + " MaxHealth: " + maxHealth
                + " aniIndex: " + aniIndex + " HitboxX: " + hitbox.x + " CurrentHealth: " + currentHealth
                + " alive: " + alive;
    }


}
