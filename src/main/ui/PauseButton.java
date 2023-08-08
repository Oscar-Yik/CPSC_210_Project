package ui;

import static ui.Constants.UI.PauseButtons.*;
import static ui.Constants.UI.SaveLoadButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
 * Represents a pause button on the pause overlay
 */
public class PauseButton extends Button {

    private int width;
    private int height;

    /*
     * EFFECTS: constructs a pause button with a given position and type
     */
    public PauseButton(int x, int y, int width, int height, int button) {
        super(x,y,button);
        this.width = width;
        this.height = height;
        createBounds();
        if (button == SAVE) {
            loadImages2();
        } else {
            loadImages();
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: imports a pause button image
     */
    @Override
    protected void loadImages() {
        int emptySpace = 2;
        int x;
        BufferedImage temp = LoadImages.getSpriteAtlas(LoadImages.PAUSE_BUTTONS);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            x = getPauseButtonPosX(button) + (i * (PAUSE_BUTTON_SIZE + emptySpace));
            imgs[i] = temp.getSubimage(x,getPauseButtonPosY(button),
                    PAUSE_BUTTON_SIZE,PAUSE_BUTTON_SIZE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: imports a 2nd type of pause button image
     */
    protected void loadImages2() {
        BufferedImage temp = LoadImages.getSpriteAtlas(LoadImages.SAVE_LOAD);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(SAVELOAD_X + (SAVELOAD_BUTTON_WIDTH * i),SAVE_Y,SAVELOAD_BUTTON_WIDTH,
                    SAVELOAD_BUTTON_HEIGHT);
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates pause button data
     */
    @Override
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    /*
     * MODIFIES: Game Panel
     * EFFECTS: draws the pause button on the game panel
     */
    @Override
    public void draw(Graphics g) {
        if (button == SAVE) {
            g.drawImage(imgs[index], posX, posY, SAVELOAD_BUTTON_WIDTH, SAVELOAD_BUTTON_HEIGHT, null);
        } else {
            g.drawImage(imgs[index], posX, posY, PAUSE_BUTTON_SIZE * 3, PAUSE_BUTTON_SIZE * 3, null);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: creates hitbox for the pause button
     */
    @Override
    public void createBounds() {
        if (button == SAVE) {
            bounds = new Rectangle(posX, posY, SAVELOAD_BUTTON_WIDTH, SAVELOAD_BUTTON_HEIGHT);
        } else {
            bounds = new Rectangle(posX,posY,width,height);
        }
    }
}
