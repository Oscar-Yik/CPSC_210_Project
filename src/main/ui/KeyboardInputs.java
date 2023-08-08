package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Represents the keyboard inputs to the game
 */
public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;

    /*
     * EFFECTS: constructs a keyboard input which reacts to the gamepanel
     */
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    /*
     * MODIFIES: gamepanel
     * EFFECTS: changes game panel depending on game state and if a key is pressed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.requestFocusInWindow();
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }

    /*
     * MODIFIES: gamePanel
     * EFFECTS: changes game panel depending on game state and if a key is released
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.requestFocusInWindow();
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
        }
    }
}
