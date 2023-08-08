package ui;

import java.awt.*;
import java.awt.event.KeyEvent;

/*
 * Represents the game over overlay that appears when the player has no health left
 */
public class GameOverOverlay {

    private Playing playing;

    /*
     * EFFECTS: constructs a game over overlay with a playing object
     */
    public GameOverOverlay(Playing playing) {
        this.playing = playing;
    }

    /*
     * EFFECTS: draws the game over overlay
     */
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.drawString("Game Over", Game.GAME_WIDTH / 2, 150);
        g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH / 2, 300);
    }

    /*
     * MODIFIES: this
     * EFFECTS: resets the game to the menu screen if escape key is pressed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.setState(Gamestate.MENU);
        }
    }


}
