package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WorldHandler {

    private Game game;
    private BufferedImage worldSprite;
    private BufferedImage[][] worldData;

    public WorldHandler(Game game) {
        this.game = game;
        worldSprite = LoadImages.getSpriteAtlas(LoadImages.FARM);
        worldData = LoadImages.getLevelData();
    }

    public void draw(Graphics g, int offsetX, int offsetY) {
        BufferedImage img = worldSprite.getSubimage(offsetX,offsetY,512,256);
        g.drawImage(img,0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT,null);
    }

    public void update() {

    }
}
