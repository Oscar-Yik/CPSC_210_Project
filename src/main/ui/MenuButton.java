package ui;

import static ui.Constants.UI.Buttons.*;
import static ui.Constants.UI.PauseButtons.*;
import static ui.Constants.UI.PauseButtons.PAUSE_BUTTON_SIZE;
import static ui.Constants.UI.SaveLoadButtons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton extends Button {

    private int offsetCenterX;
    private Gamestate state;

    public MenuButton(int posX, int posY, int button) {
        super(posX, posY, button);
        offsetCenterX = 0;
        if (button == ADD) {
            loadImages2();
        } else {
            loadImages3();
        }
        createBounds();
    }

    public MenuButton(int posX, int posY, int button, Gamestate state) {
        super(posX, posY, button);
        this.state = state;
        offsetCenterX = getButtonWidth(button) / 2;
        loadImages();
        createBounds();
    }

    @Override
    public void createBounds() {
        if (button == ADD) {
            bounds = new Rectangle(posX - offsetCenterX, posY, PAUSE_BUTTON_SIZE * 2, PAUSE_BUTTON_SIZE * 2);
        } else if (button == LOAD) {
            bounds = new Rectangle(posX - offsetCenterX, posY, SAVELOAD_BUTTON_WIDTH, SAVELOAD_BUTTON_HEIGHT);
        } else {
            bounds = new Rectangle(posX - offsetCenterX, posY, getButtonWidth(button) * 2, B_HEIGHT * 2);
        }
    }

    @Override
    protected void loadImages() {
        int emptySpace = 4;
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadImages.getSpriteAtlas(LoadImages.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(getButtonXPos(button), i * B_HEIGHT + i * emptySpace,
                    getButtonWidth(button), B_HEIGHT);
        }
    }

    protected void loadImages2() {
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

    protected void loadImages3() {
        BufferedImage temp = LoadImages.getSpriteAtlas(LoadImages.SAVE_LOAD);
        imgs = new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(SAVELOAD_X + (SAVELOAD_BUTTON_WIDTH * i),LOAD_Y,SAVELOAD_BUTTON_WIDTH,
                    SAVELOAD_BUTTON_HEIGHT);
        }
    }

    @Override
    public void draw(Graphics g) {
        if (button == ADD) {
            g.drawImage(imgs[index], posX - offsetCenterX, posY, PAUSE_BUTTON_SIZE * 2, PAUSE_BUTTON_SIZE * 2, null);
        } else if (button == LOAD) {
            g.drawImage(imgs[index], posX - offsetCenterX, posY, SAVELOAD_BUTTON_WIDTH, SAVELOAD_BUTTON_HEIGHT, null);
        } else {
            g.drawImage(imgs[index], posX - offsetCenterX, posY, getButtonWidth(button) * 2, B_HEIGHT * 2, null);
        }
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

    public void applyGamestate() {
        Gamestate.setState(state);
    }
}
