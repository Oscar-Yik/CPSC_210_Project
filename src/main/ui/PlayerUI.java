package ui;

import gamestates.Playing;
import model.Player;
import org.json.JSONObject;
import utilz.LoadImages;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;

public class PlayerUI extends Player {

    private BufferedImage[][] animations;
    private int aniTick = 25;
    private int aniIndex = 0;
    private int aniSpeed = 25;
    private int playerAction = IDLE;
    private boolean right;
    private boolean down;
    private boolean left;
    private boolean up;
    private boolean moving = false;
    private boolean attacking = false;
    private int playerSpeed = 2;
    private float drawOffsetX = 27;
    private float drawOffsetY = 4.5f;

    private Rectangle2D.Float attackBox;
    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked;
    private Playing playing;

    public PlayerUI(float x, float y, Playing playing) {
        super(x,y);
        this.playing = playing;
        loadAnimations();
        initHitbox(x,y,19 * 4.5f,30 * 4.5f);
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(posX,posY,200,200);
    }



    public void update() {
        updateHealthBar();

        if (currentHealth <= 0) {
            playing.setGameOver(true);
            return;
        }
        updateAttackBox();

        updatePosition();
        if (attacking) {
            checkAttack();
        }
        updateAnimationTick();
        setAnimation();
    }

    private void checkAttack() {
        if (attackChecked) {
            return;
        }
        attackChecked = true;
        playing.checkEnemyHit(attackBox, strength);
    }

    private void updateAttackBox() {
        if (right) {
            attackBox.x = hitbox.x + hitbox.width + 10;
        } else if (left) {
            attackBox.x = hitbox.x - hitbox.width - 10;
        }
        attackBox.y = hitbox.y + 10;
    }

    public void render(Graphics g, int offsetX, int offsetY) {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - drawOffsetX) - offsetX + flipX,
                (int) (hitbox.y - drawOffsetY) - offsetY, 144 * flipW, 144, null);
        drawHitBox(g, offsetX, offsetY);
        drawAttackBox(g, offsetX, offsetY);
        drawUI(g, offsetX, offsetY);
    }

    private void drawAttackBox(Graphics g, int offsetX, int offsetY) {
        g.setColor(Color.RED);
        g.drawRect((int) attackBox.x - offsetX,(int) attackBox.y - offsetY,
                (int) attackBox.width,(int) attackBox.height);
    }

    public void setAttackBoxX(float attackBoxX) {
        this.attackBox.x = attackBoxX;
    }

    public void setAttackBoxY(float attackBoxY) {
        this.attackBox.y = attackBoxY;
    }

    public void setAttackBoxWidth(float attackBoxWidth) {
        this.attackBox.width = attackBoxWidth;
    }

    public void setAttackBoxHeight(float attackBoxHeight) {
        this.attackBox.height = attackBoxHeight;
    }

    public void setAniTick(int aniTick) {
        this.aniTick = aniTick;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }

    public void setFlipX(int flipX) {
        this.flipX = flipX;
    }

    public void setFlipW(int flipW) {
        this.flipW = flipW;
    }

    private void setAnimation() {
        int startAni = playerAction;

        if (moving) {
            checkNorthDirections();
            if (right) {
                playerAction = WALKING_W;
            }
            checkSouthDirections();
            if (left) {
                playerAction = WALKING_W;
            }
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = ATTACKING_W;
        }

        if (startAni != playerAction) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void checkNorthDirections() {
        if (up) {
            playerAction = WALKING_N;
            if (right) {
                playerAction = WALKING_NE;
            } else if (left) {
                playerAction = WALKING_NW;
            }
        }
    }

    private void checkSouthDirections() {
        if (down) {
            playerAction = WALKING_S;
            if (right) {
                playerAction = WALKING_SE;
            } else if (left) {
                playerAction = WALKING_SW;
            }
        }
    }

    private void updatePosition() {
        moving = false;

        if (!left && !right & !up & !down) {
            return;
        }
        float speedX = 0;
        float speedY = 0;

        if (left && !right) {
            speedX = -playerSpeed;
            flipX = 0;
            flipW = 1;
        } else if (right && !left) {
            speedX = playerSpeed;
            flipX = width;
            flipW = -1;
        }

        if (up && !down) {
            speedY = -playerSpeed;
        } else if (down && !up) {
            speedY = playerSpeed;
        }

        move(speedX,speedY);
    }

    private void move(float speedX, float speedY) {
        if (canMoveHere(hitbox.x + speedX, hitbox.y + speedY, hitbox.width, hitbox.height)) {
            hitbox.x += speedX;
            hitbox.y += speedY;
            moving = true;
        }
    }

    private boolean canMoveHere(float x, float y, float width, float height) {
        float realX = x + width;
        float realY = y + height;
        if (x > 0 && realX < (Game.GAME_WIDTH * 2) - (144 * 5) && y > 0
                && realY <= (Game.GAME_HEIGHT * 2) - (144 * 3)) {
            return true;
        }
        return false;
    }

    private void loadAnimations() {
        BufferedImage img = LoadImages.getSpriteAtlas(LoadImages.PLAYER_ATLAS);
        BufferedImage img0 = LoadImages.getSpriteAtlas(LoadImages.ATTACK_ATLAS);

        animations = new BufferedImage[10][4];
        int width = 32;
        for (int j = 0; j < animations.length; j++) {
            for (int i = 0; i < animations[j].length; i++) {
                animations[j][i] = img.getSubimage(1 + (i * width),(1 + (width * j)),32,32);
            }
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= SPRITE_AMOUNT) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void resetAll() {
        resetDirBooleans();
        attacking = false;
        moving = false;
        playerAction = IDLE;
        currentHealth = maxHealth;

        hitbox.x = posX;
        hitbox.y = posY;
    }

    /*
     * EFFECTS: returns this player as a JSONObject
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
        json.put("aniTick", aniTick);
        json.put("aniIndex", aniIndex);
        json.put("right", right);
        json.put("left", left);
        json.put("up", up);
        json.put("down", down);
        json.put("flipX", flipX);
        json.put("flipW", flipW);
    }
}
