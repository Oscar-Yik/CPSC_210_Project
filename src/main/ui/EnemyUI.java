package ui;

import model.Enemy;
import model.Player;

import java.awt.geom.Rectangle2D;

import static ui.Constants.EnemyConstants.*;
import static ui.Constants.Directions.*;

/*
 * Represents the enemy character's UI
 */
public abstract class EnemyUI extends Enemy {

    protected int aniIndex = 0;
    protected int enemyState;
    protected int enemyType;
    protected int aniTick = 25;
    protected int aniSpeed = 25;
    protected float walkSpeed = 1;
    protected int walkDirX = WEST;
    protected int walkDirY = NORTH;
    protected float attackRange = 120;
    protected float visualRange = attackRange * 3;
    protected boolean attackChecked;
    protected boolean alive = true;

    /*
     * EFFECTS: constructs an EnemyUI with a given position and type
     */
    public EnemyUI(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x,y,width,height);

    }

    /*
     * MODIFIES: this
     * EFFECTS: turns the enemy towards the player
     */
    protected void turnTowardsPlayer(Player player, int offsetX, int offsetY) {
        if (player.getHitbox().x  + (offsetX * 1.5) > hitbox.x) {
            walkDirX = EAST;
        } else {
            walkDirX = WEST;
        }
        if (player.getHitbox().y + (offsetY * 1.5) > hitbox.y) {
            walkDirY = SOUTH;
        } else {
            walkDirY = NORTH;
        }
    }

    /*
     * EFFECTS: checks if player is in attack range
     */
    protected boolean isPlayerInAttackRange(Player player, int offsetX, int offsetY) {
        int distanceX = Math.abs((int) (player.getHitbox().x - offsetX - (hitbox.x - offsetX * 2.5)));
        int distanceY = Math.abs((int) (player.getHitbox().y - offsetY - (hitbox.y - offsetY * 2.5)));
        return distanceX <= attackRange && distanceY <= attackRange;
    }

    /*
     * EFFECTS: checks if player is in visual range
     */
    protected boolean isPlayerInVisualRange(Player player, int offsetX, int offsetY) {
        int distanceX = Math.abs((int) (player.getHitbox().x - offsetX - (hitbox.x - offsetX * 2.5)));
        int distanceY = Math.abs((int) (player.getHitbox().y - offsetY - (hitbox.y - offsetY * 2.5)));
        return distanceX <= visualRange || distanceY <= visualRange;
    }

    /*
     * MODIFIES: this
     * EFFECTS: moves the enemy in the x direction
     */
    protected void moveX() {
        float speedX = 0;
        if (walkDirX == WEST) {
            speedX -= walkSpeed;
        } else {
            speedX += walkSpeed;
        }
        if (canMoveHere(hitbox.x + speedX, hitbox.y, hitbox.width, hitbox.height)) {
            hitbox.x += speedX;
            return;
        }
        changeWalkDirX();
    }

    /*
     * MODIFIES: this
     * EFFECTS: moves the enemy in the y direction
     */
    protected void moveY() {
        float speedY = 0;
        if (walkDirY == NORTH) {
            speedY -= walkSpeed;
        } else {
            speedY += walkSpeed;
        }
        if (canMoveHere(hitbox.x, hitbox.y + speedY, hitbox.width, hitbox.height)) {
            hitbox.y += speedY;
            return;
        }
        changeWalkDirY();
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes enemyUI to a new animation state
     */
    protected void newState(int enemyState) {
        this.enemyState = enemyState;
        aniTick = 0;
        aniIndex = 0;
    }

    /*
     * MODIFIES: this
     * EFFECTS: lowers enemy health by certain amount
     */
    public void hurt(int amount) {
        currentHealth -= amount;
    }

    /*
     * MODIFIES: this
     * EFFECTS: increases animation tick and animation index for enemy animations
     */
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= SPRITE_AMOUNT) {
                aniIndex = 0;
                if (enemyState == ATTACKING_W) {
                    enemyState = IDLE;
                }
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes enemy x direction
     */
    protected void changeWalkDirX() {
        if (walkDirX == WEST) {
            walkDirX = EAST;
            newState(WALKING_E);
        } else {
            walkDirX = WEST;
            newState(WALKING_W);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: changes enemy y direction
     */
    protected void changeWalkDirY() {
        if (walkDirY == NORTH) {
            walkDirY = SOUTH;
            newState(WALKING_S);
        } else {
            walkDirY = NORTH;
            newState(WALKING_N);
        }
    }

    /*
     * EFFECTS: checks boundary of the world
     */
    protected boolean canMoveHere(float x, float y, float width, float height) {
        float realX = x + width;
        float realY = y + height;
        if (x > 0 && realX < (Game.GAME_WIDTH * 2) && y > 0
                && realY <= (Game.GAME_HEIGHT * 2)) {
            return true;
        }
        return false;
    }

    public int getEnemyIndex() {
        return aniIndex;
    }

    public int getEnemyState() {
        return enemyState;
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if the enemy is hit by the player
     */
    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player, int offsetX, int offsetY) {
        Rectangle2D.Float hitbox = player.getHitbox();

        Rectangle2D.Float actualAttackBox = new Rectangle2D.Float(attackBox.x - offsetX * 2.5f,
                attackBox.y - offsetY * 2.5f, attackBox.width, attackBox.height);

        Rectangle2D.Float actualHitBox = new Rectangle2D.Float(hitbox.x - offsetX,
                hitbox.y - offsetY, hitbox.width, hitbox.height);

        if (actualAttackBox.intersects(actualHitBox)) {
            player.changeHealth(-strength);
        }
        attackChecked = true;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }

    public void setEnemyState(int enemyState) {
        this.enemyState = enemyState;
    }

    public void setAniTick(int aniTick) {
        this.aniTick = aniTick;
    }

    public void setWalkDirX(int walkDirX) {
        this.walkDirX = walkDirX;
    }

    public void setWalkDirY(int walkDirY) {
        this.walkDirY = walkDirY;
    }

    /*
     * MODIFIES: this
     * EFFECTS: resets all enemy booleans
     */
    public void resetEnemy() {
        hitbox.x = posX;
        hitbox.y = posY;
        currentHealth = maxHealth;
        enemyState = IDLE;
        setAlive(true);
    }
}
