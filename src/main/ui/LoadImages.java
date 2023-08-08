package ui;

import ui.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * Represents a class that imports all images from data folder to a usable array
 * Art citations:
 * Blue_Cavalier_Sprite: https://www.spriters-resource.com/3ds/fireemblemawakening/sheet/64153/
 * Buttons: https://bdragon1727.itch.io/basic-pixel-gui-and-buttons-pack-2
 * Empty Health Bar + Health + Pause_Components: https://mounirtohami.itch.io/pixel-art-gui-elements
 * farm: https://pingudroid.files.wordpress.com/2022/03/farm.png?w=1024
 * Menu_Background_2: https://pro2-bar-s3-cdn-cf5.myportfolio.com/6eeae5a2e91d6f4cf1067e6935759146/69e4cc7c-29a2-472c-
 * b35f-a899ac5ef803_rw_1920.jpg?h=c742ebc7dea9104a66476894ad713745
 * Morgan_Sprite: https://www.spriters-resource.com/3ds/fireemblemawakening/sheet/64160/
 * Pause_Background: https://art.pixilart.com/3a8a19a3fff549b.png
 * Pause_Buttons: https://bdragon1727.itch.io/basic-pixel-gui-and-buttons-pack-1
 * UI_BUttons: https://bdragon1727.itch.io/platformer-ui-buttons
 */
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

    /*
     * MODIFIES: this
     * EFFECTS: returns array with image data from given file
     */
    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("data/" + filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return img;
    }

    /*
     * MODIFIES: this
     * EFFECTS: returns world image as an array
     */
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
