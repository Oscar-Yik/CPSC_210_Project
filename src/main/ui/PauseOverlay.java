package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import gui.PauseButton;
import utilz.LoadImages;

import static java.awt.Font.BOLD;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.SaveLoadButtons.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PauseOverlay {

    private BufferedImage backgroundImg;
    private BufferedImage backgroundHeader;
    private int bgX;
    private int bgY;
    private int bgW;
    private int bgH;
    private PauseButton home;
    private PauseButton unpause;
    private PauseButton save;
    private Playing playing;

    public PauseOverlay(Playing playing) {
        loadBackground();
        createPauseButtons();
        this.playing = playing;
    }

    private void createPauseButtons() {
        int homeX = bgX + 50;
        int unpauseX = homeX + 100;
        int saveX = homeX + 75;
        int buttonY = bgY + 125;

        home = new PauseButton(homeX, buttonY, PAUSE_BUTTON_SIZE * 3, PAUSE_BUTTON_SIZE * 3, HOME);
        unpause = new PauseButton(unpauseX, buttonY, PAUSE_BUTTON_SIZE * 3, PAUSE_BUTTON_SIZE * 3, CONTINUE);
        save = new PauseButton(saveX, buttonY + 100, PAUSE_BUTTON_SIZE * 3, PAUSE_BUTTON_SIZE * 3, SAVE);
    }

    private void loadBackground() {
        backgroundImg = LoadImages.getSpriteAtlas(LoadImages.PAUSE_BACKGROUND);
        BufferedImage temp = LoadImages.getSpriteAtlas(LoadImages.PAUSE_COMPONENTS);
        backgroundHeader = temp.getSubimage(49,97,30,30);
        bgW = 300;
        bgH = 300;
        bgX = (Game.GAME_WIDTH / 2) - (bgW / 2);
        bgY = 100;
    }

    public void update() {
        home.update();
        unpause.update();
        save.update();
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        g.drawImage(backgroundHeader, bgX + 50, bgY + 25, 200, 50, null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Courier", BOLD,30));
        g.drawString("Pause Menu", bgX + 65, bgY + 50);

        home.draw(g);
        unpause.draw(g);
        save.draw(g);
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e,home)) {
            home.setMousePressed(true);
        } else if (isIn(e,unpause)) {
            unpause.setMousePressed(true);
        } else if (isIn(e,save)) {
            save.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e,home)) {
            if (home.isMousePressed()) {
                Gamestate.setState(Gamestate.MENU);
                playing.unpauseGame();
            }
        } else if (isIn(e,unpause)) {
            if (unpause.isMousePressed()) {
                playing.unpauseGame();
            }
        } else if (isIn(e,save)) {
            if (save.isMousePressed()) {
                playing.saveGame();
            }
        }
        home.resetBools();
        unpause.resetBools();
        save.resetBools();
    }

    public void mouseMoved(MouseEvent e) {
        home.setMouseOver(false);
        unpause.setMouseOver(false);
        save.setMouseOver(false);
        
        if (isIn(e,home)) {
            home.setMouseOver(true);
        } else if (isIn(e,unpause)) {
            unpause.setMouseOver(true);
        } else if (isIn(e,save)) {
            save.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, PauseButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }
}
