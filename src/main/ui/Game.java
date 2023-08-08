package ui;

import persistence.JsonReader;

import java.awt.*;
import java.io.IOException;

/*
 * Represents the game that can be played
 */
public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int fpsSet = 120;
    private final int upsSet = 200;
    private Playing playing;
    private Menu menu;
    private JsonReader jsonReader;
    private int numEnemies = 1;

    private static final String JSON_STORE = "./data/world.json";
    public static final int GAME_WIDTH = 1248;
    public static final int GAME_HEIGHT = 672;

    /*
     * EFFECTS: constructs a game with a panel, window, and starts the game loop
     */
    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        gamePanel.setFocusable(true);
        startGameLoop();
    }

    /*
     * MODIFIES: this
     * EFFECTS: initializes all classes
     */
    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /*
     * MODIFIES: this
     * EFFECTS: starts the game loop
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*
     * MODIFIES: this
     * EFFECTS: updates game data
     */
    public void update() {

        switch (Gamestate.getState()) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    /*
     * EFFECTS: draws the game
     */
    public void render(Graphics g) {

        switch (Gamestate.getState()) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: runs game loop and separates tasks to different threads
     */
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / fpsSet;
        double timePerUpdate = 1000000000.0 / upsSet;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0.0;
        double deltaF = 0.0;
        runGameLoop(timePerFrame,timePerUpdate,previousTime,frames,updates,lastCheck,deltaU,deltaF);
    }

    /*
     * MODIFIES: this
     * EFFECTS: runs the game loop
     */
    private void runGameLoop(double timePerFrame, double timePerUpdate, long previousTime, int frames, int updates,
                             long lastCheck, double deltaU, double deltaF) {
        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS " + frames + " | UPS " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: checks if player is offscreen
     */
    public void windowFocusLost() {
        if (Gamestate.getState() == Gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Playing getPlaying() {
        return this.playing;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds number of enemies to the game
     */
    public void addEnemies() {
        numEnemies++;
    }

    public int getNumEnemies() {
        return this.numEnemies;
    }

    public void setNumEnemies(int numEnemies) {
        this.numEnemies = numEnemies;
    }

    /*
     * MODIFIES: this
     * EFFECTS: loads game save data
     */
    public void loadGame() {
        try {
            playing = jsonReader.read(this);
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
