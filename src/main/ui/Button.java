package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Button {

    protected int posX;
    protected int posY;
    protected int button;
    protected boolean mouseOver;
    protected boolean mousePressed;
    protected Rectangle bounds;
    protected int index;
    protected BufferedImage[] imgs;

    public Button(int posX, int posY, int button) {
        this.posX = posX;
        this.posY = posY;
        this.button = button;
    }

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract void createBounds();

    protected abstract void loadImages();

    public Rectangle getBounds() {
        return this.bounds;
    }

    public void resetBools() {
        mousePressed = false;
        mouseOver = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
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
