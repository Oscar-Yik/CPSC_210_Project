package ui;

import static ui.Constants.UI.Buttons.*;
import static ui.Constants.UI.PauseButtons.*;
import static ui.Constants.UI.SaveLoadButtons.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImg;


    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backgroundImg = LoadImages.getSpriteAtlas(LoadImages.MENU_BACKGROUND_2);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, 150, START, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, 214, QUIT, Gamestate.QUIT);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, 300, ADD);
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, 360, LOAD);
    }

    @Override
    public void update() {
        for (MenuButton menuButton : buttons) {
            menuButton.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg,0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        for (MenuButton menuButton : buttons) {
            menuButton.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            if (isIn(e,menuButton)) {
                menuButton.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            if (isIn(e,menuButton)) {
                if (menuButton.isMousePressed()) {
                    if (menuButton.getButton() == ADD) {
                        game.addEnemies();
                    } else if (menuButton.getButton() == LOAD) {
                        game.loadGame();
                    } else {
                        menuButton.applyGamestate();
                    }
                }
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton menuButton : buttons) {
            menuButton.resetBools();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            menuButton.setMouseOver(false);
        }
        for (MenuButton menuButton : buttons) {
            if (isIn(e,menuButton)) {
                menuButton.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.setState(Gamestate.PLAYING);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
