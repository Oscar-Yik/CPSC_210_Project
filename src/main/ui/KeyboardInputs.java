package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    private GamePanel gamePanel;


    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

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
