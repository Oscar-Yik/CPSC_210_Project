package utilz;

public class Constants {

    public static class UI {
        public static class Buttons {
            public static final int NEW_GAME = 0;
            public static final int CONTINUE = 1;
            public static final int RESTART = 2;
            public static final int START = 3;
            public static final int CREDITS = 4;
            public static final int MENU = 5;
            public static final int QUIT = 6;

            public static final int B_HEIGHT = 28;

            public static int getButtonWidth(int button) {
                switch (button) {
                    case NEW_GAME:
                    case CONTINUE:
                        return 96;
                    case RESTART:
                    case CREDITS:
                        return 80;
                    case START:
                    case MENU:
                    case QUIT:
                        return 64;
                    default:
                        return 0;
                }
            }

            public static int getButtonXPos(int button) {
                switch (button) {
                    case NEW_GAME:
                        return 0;
                    case CONTINUE:
                        return 96;
                    case RESTART:
                        return 192;
                    case START:
                        return 192 + 80;
                    case CREDITS:
                        return 192 + 80 + 64;
                    case MENU:
                        return 192 + (2 * 80) + 64;
                    case QUIT:
                        return 192 + (2 * 80) + (2 * 64);
                    default:
                        return 1;
                }
            }
        }

        public static class PauseButtons {
            public static final int PAUSE_BUTTON_SIZE = 30;

            public static final int CONTINUE = 0;
            public static final int HOME = 1;
            public static final int ADD = 2;

            public static int getPauseButtonPosX(int button) {
                switch (button) {
                    case CONTINUE:
                        return 97;
                    case HOME:
                        return 289;
                    case ADD:
                        return 193;
                    default:
                        return 0;
                }
            }

            public static int getPauseButtonPosY(int button) {
                switch (button) {
                    case CONTINUE:
                        return 97;
                    case HOME:
                        return 1;
                    case ADD:
                        return 257;
                    default:
                        return 0;
                }
            }

        }

        public static class SaveLoadButtons {
            public static final int SAVELOAD_BUTTON_WIDTH = 64;
            public static final int SAVELOAD_BUTTON_HEIGHT = 32;

            public static final int SAVE = 4;
            public static final int LOAD = 5;

            public static final int SAVELOAD_X = 1;
            public static final int SAVE_Y = 353;
            public static final int LOAD_Y = 385;
        }

    }


    public static class Directions {
        public static final int WEST = 0;
        public static final int NORTH = 1;
        public static final int EAST = 2;
        public static final int SOUTH = 3;

        public static final int NORTHWEST = 4;
        public static final int NORTHEAST = 5;
        public static final int SOUTHWEST = 6;
        public static final int SOUTHEAST = 7;

    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int ATTACKING_W = 1;
        public static final int WALKING_W = 2;
        public static final int WALKING_E = 3;
        public static final int WALKING_S = 4;
        public static final int WALKING_N = 5;
        public static final int WALKING_SW = 6;
        public static final int WALKING_SE = 7;
        public static final int WALKING_NE = 8;
        public static final int WALKING_NW = 9;

        public static final int RED_PLAYER = 0;
        public static final int BLUE_PLAYER = 1;
        public static final int GREEN_PLAYER = 2;

        public static final int SPRITE_AMOUNT = 4;
    }

    public static class EnemyConstants {
        public static final int CAVALIER = 0;

        public static final int IDLE = 0;
        public static final int ATTACKING_W = 1;
        public static final int WALKING_W = 2;
        public static final int WALKING_E = 3;
        public static final int WALKING_S = 4;
        public static final int WALKING_N = 5;
        public static final int WALKING_SW = 6;
        public static final int WALKING_SE = 7;
        public static final int WALKING_NW = 8;
        public static final int WALKING_NE = 9;

        public static final int SPRITE_AMOUNT = 4;

        public static final int CAVALIER_SCALE = 2;
        public static final int CAVALIER_INITIAL_X = 3;
        public static final int CAVALIER_INITIAL_Y = 9;
        public static final int CAVALIER_DEFAULT_WIDTH = 83;
        public static final int CAVALIER_DEFAULT_HEIGHT = 83;
        public static final int CAVALIER_WIDTH = CAVALIER_DEFAULT_WIDTH * CAVALIER_SCALE;
        public static final int CAVALIER_HEIGHT = CAVALIER_DEFAULT_HEIGHT * CAVALIER_SCALE;

        public static final int CAVALIER_DRAWOFFSET_X = 0;
        public static final int CAVALIER_DRAWOFFSET_Y = 0;

    }
}
