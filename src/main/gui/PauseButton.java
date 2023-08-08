package gui;

import utilz.LoadImages;
import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.SaveLoadButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseButton extends Button {

    private int width;
    private int height;

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

    @Override
    public void draw(Graphics g) {
        if (button == SAVE) {
            g.drawImage(imgs[index], posX, posY, SAVELOAD_BUTTON_WIDTH, SAVELOAD_BUTTON_HEIGHT, null);
        } else {
            g.drawImage(imgs[index], posX, posY, PAUSE_BUTTON_SIZE * 3, PAUSE_BUTTON_SIZE * 3, null);
        }
    }

    @Override
    public void createBounds() {
        if (button == SAVE) {
            bounds = new Rectangle(posX, posY, SAVELOAD_BUTTON_WIDTH, SAVELOAD_BUTTON_HEIGHT);
        } else {
            bounds = new Rectangle(posX,posY,width,height);
        }
    }
}
