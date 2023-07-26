package ui;

import model.Enemy;
import model.Player;
import model.World;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Game application
public class GameApp {

    private static final String JSON_STORE = "./data/world.json";
    private World world;
    private Scanner input;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: runs the game application
    public GameApp() {
        runGame();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runGame() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            startMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                start();
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes world and adds 2 enemies to it
    private void init() {

        world = new World("Hero");
        world.addMonsters();
        world.addMonsters();

        input = new Scanner(System.in);
        input.useDelimiter("\n");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    // EFFECTS: displays start menu for users
    private void startMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Start Game");
        System.out.println("\tq -> quit");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input and executes corresponding
     * actions.
     */
    private void start() {
        boolean keepGoing = true;
        String action;

        while (keepGoing) {
            actionMenu();
            action = input.next();
            action = action.toLowerCase();

            if (action.equals("e")) {
                keepGoing = false;
            } else if (action.equals("a")) {
                boolean end = attackEnemies();
                if (end) {
                    break;
                }
            } else {
                processCommand(action);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            printWorld();
        } else if (command.equals("s")) {
            saveWorld();
        } else if (command.equals("l")) {
            loadWorld();
        } else if (command.equals("m")) {
            moveCharacter();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    /*
     * EFFECTS: Displays action menu for users
     */
    private void actionMenu() {
        System.out.println("\nWhat do you want to do?:");
        System.out.println("\ta -> Attack Enemies");
        System.out.println("\tc -> Check World Stats");
        System.out.println("\tm -> Move Character");

        System.out.println("\ts -> Save World");
        System.out.println("\tl -> Load World");

        System.out.println("\te -> End Game");
    }

    /*
     * MODIFIES: this
     * EFFECTS: monsters lose health depending on which one is targeted. If
     * all monsters have 0 health, asks the user to play again or quit the game
     */
    private boolean attackEnemies() {
        boolean reset;
        displayMonsters();

        battle(targetMonsters());

        if (noMoreMonsters()) {
            reset = playAgain();
            if (reset) {
                return true;
            }
            increaseDifficulty();
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: user targets which monster to attack
     */
    private int targetMonsters() {
        int count = 1;
        boolean choosing = true;
        String action;
        while (choosing) {
            System.out.println("\nCurrent Target: " + count);
            System.out.println("\tt -> Target this monster");
            System.out.println("\tn -> Next monster");

            action = input.next();
            action = action.toLowerCase();

            if (action.equals("t")) {
                choosing = false;

            } else if (count + 1 > world.getMonsters().size()) {
                count = 1;
            } else if (action.equals("n")) {
                count++;
            }
        }
        return (count - 1);
    }


    /*
     * MODIFIES: world
     * EFFECTS: Allows user to add as many monsters as wanted with
     * their level scaled to the player's level
     */
    private void increaseDifficulty() {
        System.out.println("How many more monsters do you want to add?");
        int numMonsters = input.nextInt();
        for (int i = 0; i < (numMonsters + 2); i++) {
            world.addMonsters();
        }
        for (Enemy enemy: world.getMonsters()) {
            enemy.characterScale(world.getPlayer());
        }
    }

    /*
     * EFFECTS: returns true if all monsters have no more health,
     * false otherwise
     */
    private boolean noMoreMonsters() {
        for (Enemy enemy: world.getMonsters()) {
            if (enemy.getHealth() != 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * EFFECTS: Displays monsters and their stats
     */
    private void displayMonsters() {
        System.out.println("\nWhich monster do you want to attack?:");
        int count = 1;
        for (Enemy enemy: world.getMonsters()) {
            if (enemy.getHealth() != 0) {
                System.out.println("\tMonster" + count);
                System.out.println("\t\t" + enemy);
            }
            count++;
        }
    }

    /*
     * MODIFIES: this, world
     * EFFECTS: Removes monster's health and add player's exp
     * depending on which monster is chosen. If monster's health is zero
     * it is removed from the world. Also displays messages for each action
     */
    private void battle(int monsterIndex) {
        Enemy targetMonster = world.getMonsters().get(monsterIndex);
        Player player = world.getPlayer();
        targetMonster.takeDamage(player.getStrength());
        if (targetMonster.getHealth() == 0) {
            world.getMonsters().remove(monsterIndex);
            int iniLevel = player.getLevel().size();
            player.addEXP();
            if (player.getLevel().size() > iniLevel) {
                System.out.println("You Leveled Up!");
            }
        }
        System.out.println("Monster " + (monsterIndex + 1) + " Took " + player.getDamage() + " Damage!");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Create new enemy objects and continues or ends game
     * depending on user's input as a boolean
     */
    private boolean playAgain() {
        boolean reset;
        System.out.println("Monsters Defeated!");
        System.out.println("Play Again?");
        System.out.println("\ty -> Yes");
        System.out.println("\tn -> No");

        String restart;
        restart = input.next();
        restart = restart.toLowerCase();

        if (restart.equals("y")) {
            reset = false;
        } else {
            reset = true;
        }

        world.resetMonsters();
        return reset;
    }

    /*
     * EFFECTS: Processes user input for which direction to move
     * and outputs result
     */
    private void moveCharacter() {
        String direction;
        System.out.println("\nSelect which direction to move:");
        System.out.println("\tw -> UP");
        System.out.println("\ta -> LEFT");
        System.out.println("\ts -> DOWN");
        System.out.println("\td -> RIGHT");
        direction = input.next();
        direction = direction.toLowerCase();

        switch (direction) {
            case "w":
                System.out.println("Player moved up");
                break;
            case "a":
                System.out.println("Player moved left");
                break;
            case "s":
                System.out.println("Player moved down");
                break;
            case "d":
                System.out.println("Player moved right");
                break;
        }
    }

    // EFFECTS: prints the world's information
    private void printWorld() {
        System.out.println(world);
    }

    // EFFECTS: saves the world to file
    private void saveWorld() {
        try {
            jsonWriter.open();
            jsonWriter.write(world);
            jsonWriter.close();
            System.out.println("Saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads world from file
    private void loadWorld() {
        try {
            world = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
