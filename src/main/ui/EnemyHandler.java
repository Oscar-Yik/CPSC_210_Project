package ui;

import gamestates.Playing;
import model.Cavalier;
import model.Enemy;
import model.Player;
import utilz.LoadImages;
import static utilz.Constants.EnemyConstants.*;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class EnemyHandler {

    private Playing playing;
    private BufferedImage[][] cavalier;
    private ArrayList<Cavalier> cavaliers = new ArrayList<>();
    private Random random;
    private int numCavaliers = 0;
    private int numAlive = 0;

    public EnemyHandler(Playing playing) {
        this.playing = playing;
    }

    public void addEnemies() {
        random = new Random();
        for (int i = 0; i < numCavaliers; i++) {
            cavaliers.add(new Cavalier(random.nextInt(Game.GAME_WIDTH),random.nextInt(Game.GAME_HEIGHT)));
        }
        scaleEnemies();
    }

    private void scaleEnemies() {
        for (Cavalier c : cavaliers) {
            c.characterScale(playing.getPlayer());
            c.setCurrentHealth();
        }
    }

    public void update(Player player, int offsetX, int offsetY, int numEnemies) {
        if (numEnemies > numCavaliers) {
            numCavaliers = numEnemies;
            numAlive = numCavaliers;
            loadEnemyImages();
            removeEnemies();
            addEnemies();
        }

        for (Cavalier c : cavaliers) {
            if (c.isAlive()) {
                c.update(player, offsetX, offsetY);
                c.updateHealthBar();
                if (c.getCurrentHealth() <= 0) {
                    c.setAlive(false);
                    numAlive--;
                    player.levelUp();
                }
            }
        }
        if (numAlive == 0) {
            playing.setGameWin(true);
        }
    }

    private void removeEnemies() {
        cavaliers.clear();
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        drawCavaliers(g, offsetX, offsetY);
    }

    private void drawCavaliers(Graphics g, int offsetX, int offsetY) {

        for (Cavalier c : cavaliers) {
            if (cavalier == null) {
                loadEnemyImages();
            }
            if (c.isAlive()) {
                g.drawImage(cavalier[c.getEnemyState()][c.getEnemyIndex()], (int) (c.getHitbox().x - (offsetX * 2.5)),
                        (int) (c.getHitbox().y - (offsetY * 2.5)), CAVALIER_WIDTH, CAVALIER_HEIGHT, null);
                c.drawHitBox(g,offsetX,offsetY);
                c.drawUI(g, (int) (offsetX * 2.5), (int)(offsetY * 2.5));
                c.drawAttackBox(g, offsetX, offsetY);
            }
        }
    }

    private void loadEnemyImages() {
        cavalier = new BufferedImage[10][4];
        BufferedImage temp = LoadImages.getSpriteAtlas(LoadImages.CAVALIER_SPRITE);
        for (int j = 0; j < cavalier.length; j++) {
            for (int i = 0; i < cavalier[j].length; i++) {
                if (i == cavalier[j].length - 1) {
                    cavalier[j][i] = temp.getSubimage(CAVALIER_INITIAL_X + (CAVALIER_DEFAULT_WIDTH * i),
                            CAVALIER_INITIAL_Y + (CAVALIER_DEFAULT_HEIGHT * j), 75,
                            CAVALIER_DEFAULT_HEIGHT);
                } else {
                    cavalier[j][i] = temp.getSubimage(CAVALIER_INITIAL_X + (CAVALIER_DEFAULT_WIDTH * i),
                            CAVALIER_INITIAL_Y + (CAVALIER_DEFAULT_HEIGHT * j), CAVALIER_DEFAULT_WIDTH,
                            CAVALIER_DEFAULT_HEIGHT);
                }
            }
        }
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox, int offsetX, int offsetY, int strength) {

        Rectangle2D.Float actualAttackBox = new Rectangle2D.Float(attackBox.x - offsetX,
                attackBox.y - offsetY, attackBox.width, attackBox.height);

        for (Cavalier c : cavaliers) {
            Rectangle2D.Float hitbox = c.getHitbox();
            Rectangle2D.Float actualHitBox = new Rectangle2D.Float(hitbox.x - offsetX * 2.5f,
                    hitbox.y - offsetY * 2.5f, hitbox.width, hitbox.height);

            if (actualAttackBox.intersects(actualHitBox)) {
                c.hurt(strength);
                return;
            }
        }
    }

    public void resetAllEnemies() {
        for (Cavalier c : cavaliers) {
            c.resetEnemy();
        }
    }

    public ArrayList<Cavalier> getEnemies() {
        return cavaliers;
    }

    public int getNumAlive() {
        return numAlive;
    }

    public void setNumAlive(int numAlive) {
        this.numAlive = numAlive;
    }

    public void setNumCavaliers(int numCavaliers) {
        this.numCavaliers = numCavaliers;
    }

    public ArrayList<Cavalier> getCavaliers() {
        return cavaliers;
    }

    public void addCavalier(Cavalier cavalier) {
        cavaliers.add(cavalier);
    }
}
