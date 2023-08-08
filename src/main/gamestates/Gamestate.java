package gamestates;

public enum Gamestate {
    PLAYING, MENU, QUIT;

    private static Gamestate state = MENU;

    public static Gamestate getState() {
        return state;
    }

    public static void setState(Gamestate state) {
        Gamestate.state = state;
    }
}
