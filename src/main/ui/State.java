package ui;

import java.awt.event.MouseEvent;

/*
 * Represents the State the game is in
 */
public class State {

    protected Game game;

    /*
     * Constructs a state of the game
     */
    public State(Game game) {
        this.game = game;
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if mouse is in the menu button
     */
    public boolean isIn(MouseEvent e, MenuButton mb) {
        return mb.getBounds().contains(e.getX(),e.getY());
    }

    public Game getGame() {
        return this.game;
    }
}
