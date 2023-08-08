package utilz;

import ui.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoadImages {

    public static final String PLAYER_ATLAS = "Morgan_Sprite.png";
    public static final String ATTACK_ATLAS = "Attack.png";
    public static final String FARM = "farm.png";
    public static final String MENU_BUTTONS = "UI_Buttons.png";
    public static final String MENU_BACKGROUND_2 = "Menu_Background_2.png";
    public static final String PAUSE_BACKGROUND = "Pause_Background.png";
    public static final String PAUSE_COMPONENTS = "Pause_Components.png";
    public static final String PAUSE_BUTTONS = "Pause_Buttons.png";
    public static final String CAVALIER_SPRITE = "Blue_Cavalier_Sprite.png";
    public static final String HEALTH_BAR = "Empty Health Bar.png";
    public static final String HEALTH = "Health.png";
    public static final String SAVE_LOAD = "Buttons.png";

    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("data/" + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    public static BufferedImage[][] getLevelData() {
        BufferedImage img = getSpriteAtlas(FARM);
        BufferedImage[][] lvlData = new BufferedImage[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                lvlData[j][i] = img.getSubimage(i,j,1,1);
            }
        }
        return lvlData;
    }
}
