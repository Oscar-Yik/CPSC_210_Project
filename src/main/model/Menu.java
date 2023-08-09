package model;

import ui.*;

import static ui.Constants.UI.Buttons.*;
import static ui.Constants.UI.PauseButtons.*;
import static ui.Constants.UI.SaveLoadButtons.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/*
 * Represents the menu game state which starts the game
 */
public class Menu extends State {

    private MenuButton[] buttons = new MenuButton[4];
    private BufferedImage backgroundImg;

    /*
     * EFFECTS: constructs a menu in the game
     */
    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    /*
     * MODIFIES: this
     * EFFECTS: imports the menu background image to the game
     */
    private void loadBackground() {
        backgroundImg = LoadImages.getSpriteAtlas(LoadImages.MENU_BACKGROUND_2);
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates buttons on the menu panel
     */
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, 150, START, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, 214, QUIT, Gamestate.QUIT);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, 300, ADD);
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, 360, LOAD);
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates menu data
     */
    @Override
    public void update() {
        for (MenuButton menuButton : buttons) {
            menuButton.update();
        }
    }

    /*
     * MODIFIES: gamePanel
     * EFFECTS: draws menu on the gamePanel
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: checks if mouse is pressed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            if (isIn(e,menuButton)) {
                menuButton.setMousePressed(true);
                break;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if mouse is released depending on the button pressed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton menuButton : buttons) {
            if (isIn(e,menuButton)) {
                if (menuButton.isMousePressed()) {
                    if (menuButton.getButton() == ADD) {
                        game.addEnemies();
                        EventLog.getInstance().logEvent(new Event("Added an enemy"));
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

    /*
     * MODIFIES: this
     * EFFECTS: resets the state of all menu buttons
     */
    private void resetButtons() {
        for (MenuButton menuButton : buttons) {
            menuButton.resetBools();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if mouse moved over the menu buttons
     */
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

    /*
     * MODIFIES: this
     * EFFECTS: checks if enter key was pressed to start game
     */
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
