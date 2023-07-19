package ui;

import model.Enemy;
import model.Player;

import java.util.Scanner;


// Game application
public class GameApp {

    private Player player;
    private Enemy monster1;
    private Enemy monster2;
    private Scanner input;

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
    // EFFECTS: initializes player and enemies
    private void init() {

        player = new Player("Hero");
        monster1 = new Enemy();
        monster2 = new Enemy();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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

            if (action.equals("a")) {
                boolean end = attackEnemies();
                if (end) {
                    break;
                }
            } else if (action.equals("c")) {
                stats();
            } else if (action.equals("e")) {
                keepGoing = false;
            } else {
                moveCharacter();
            }
        }

    }

    /*
     * EFFECTS: Displays action menu for users
     */
    private void actionMenu() {
        System.out.println("\nWhat do you want to do?:");
        System.out.println("\ta -> Attack Enemies");
        System.out.println("\tc -> Check Stats");
        System.out.println("\tm -> Move Character");
        System.out.println("\te -> End Game");
    }

    /*
     * MODIFIES: this
     * EFFECTS: monsters lose health depending on which one is targeted. If
     * all monsters have 0 health, asks the user to play again or quit the game
     */
    private boolean attackEnemies() {
        String monsterChoice;
        boolean reset;
        targetMonster();
        monsterChoice = input.next();
        monsterChoice = monsterChoice.toLowerCase();

        battle(monsterChoice);

        if (monster2.getHealth() == 0 && monster1.getHealth() == 0) {
            reset = playAgain();
            if (reset) {
                return true;
            }
        }
        return false;
    }

    /*
     * EFFECTS: Displays monsters to target and their stats depending
     * on how many of them have health greater than 0
     */
    private void targetMonster() {
        if (monster2.getHealth() == 0) {
            System.out.println("\nWhich monster do you want to attack?:");
            System.out.println("\ta -> Attack Monster 1");
            System.out.println("\t\t" + monster1);
        } else if (monster1.getHealth() == 0) {
            System.out.println("\nWhich monster do you want to attack?:");
            System.out.println("\tb -> Attack Monster 2");
            System.out.println("\t\t" + monster2);
        } else {
            System.out.println("\nWhich monster do you want to attack?:");
            System.out.println("\ta -> Attack Monster 1");
            System.out.println("\t\t" + monster1);
            System.out.println("\tb -> Attack Monster 2");
            System.out.println("\t\t" + monster2);
        }
    }

    /*
     * REQUIRES: monsterChoice is either "a" or "b"
     * MODIFIES: this
     * EFFECTS: Removes monster's health and add player's exp
     * depending on which monster is chosen. Also displays messages for
     * each action
     */
    private void battle(String monsterChoice) {
        if (monsterChoice.equals("a")) {
            monster1.takeDamage(player.getStrength());
            if (monster1.getHealth() == 0) {
                player.addEXP();
                System.out.println("You Leveled Up!");
            }
            System.out.println("Monster 1 Took " + player.getDamage() + " Damage!");
        } else if (monsterChoice.equals("b")) {
            monster2.takeDamage(player.getStrength());
            if (monster2.getHealth() == 0) {
                player.addEXP();
                System.out.println("You Leveled Up!");
            }
            System.out.println("Monster 1 Took " + player.getDamage() + " Damage!");
        }
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

        monster1 = new Enemy();
        monster2 = new Enemy();
        monster1.characterScale(player);
        monster2.characterScale(player);
        return reset;
    }

    /*
     * EFFECTS: Displays player's stats
     */
    private void stats() {
        System.out.println("Level: " + player.getLevel().size());
        System.out.println("Strength: " + player.getStrength());
        System.out.println("Health: " + player.getHealth());
        System.out.println("Movement Speed: " + player.getMovementSpeed());
        System.out.println("Range: " + player.getRange());
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
}
