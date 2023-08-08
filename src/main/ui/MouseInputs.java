package ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * Represents the mouse inputs to the game
 */
public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;

    /*
     * EFFECTS: contructs a mouse input object that interacts with the game Panel
     */
    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    /*
     * MODIFIES: game panel
     * EFFECTS: checks if mouse is clicked
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (Gamestate.getState()) {
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    /*
     * MODIFIES: Game Panel
     * EFFECTS: checks if mouse moved on the gamepanel
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().mouseMoved(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            default:
                break;
        }
    }

    /*
     * MODIFIES: Game Panel
     * EFFECTS: checks if mouse pressed on the gamepanel
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().mousePressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            default:
                break;
        }
    }

    /*
     * MODIFIES: Game Panel
     * EFFECTS: checks if mouse is released on the gamepanel
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (Gamestate.getState()) {
            case MENU:
                gamePanel.getGame().getMenu().mouseReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }
}
