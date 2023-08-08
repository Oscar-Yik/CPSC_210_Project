package ui;

import java.awt.*;
import java.awt.event.KeyEvent;

/*
 * Represents the overlay when the player beats all the enemies
 */
public class GameWinOverlay {

    private Playing playing;

    /*
     * EFFECTS: constructs a game win overlay in the playing game state
     */
    public GameWinOverlay(Playing playing) {
        this.playing = playing;
    }

    /*
     * EFFECTS: draws the game win overlay
     */
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0,Game.GAME_WIDTH,Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.drawString("Game Win", Game.GAME_WIDTH / 2, 150);
        g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH / 2, 300);
    }

    /*
     * MODIFIES: this
     * EFFECTS: returns to the menu screen if escape key is pressed
     */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.setState(Gamestate.MENU);
        }
    }

}
