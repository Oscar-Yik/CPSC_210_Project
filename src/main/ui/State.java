package ui;

import model.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/*
 * Represents the State the game is in
 */
public abstract class State {

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

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);
}
