package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Represents a Button that is displayed on the screen
 */
public abstract class Button {

    protected int posX;
    protected int posY;
    protected int button;
    protected boolean mouseOver;
    protected boolean mousePressed;
    protected Rectangle bounds;
    protected int index;
    protected BufferedImage[] imgs;

    /*
     * EFFECTS: Constructs a Button with a given x and y position
     *          and a type of button
     */
    public Button(int posX, int posY, int button) {
        this.posX = posX;
        this.posY = posY;
        this.button = button;
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates button data
     */
    public abstract void update();

    /*
     * EFFECTS: draws button
     */
    public abstract void draw(Graphics g);

    /*
     * MODIFIES: this
     * EFFECTS: creates hitbox for button
     */
    public abstract void createBounds();

    /*
     * MODIFIES: this
     * EFFECTS: imports images from data folder to array
     */
    protected abstract void loadImages();

    public Rectangle getBounds() {
        return this.bounds;
    }

    /*
     * MODIFIES: this
     * EFFECTS: resets mouse booleans
     */
    public void resetBools() {
        mousePressed = false;
        mouseOver = false;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public int getButton() {
        return this.button;
    }
}
