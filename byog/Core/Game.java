package byog.Core;

import byog.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;

import java.io.Serializable;

import static byog.Core.Save.loadGame;


public class Game implements Serializable {
    long seed;

    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    static boolean inputString = true;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {

        StdDraw.setCanvasSize(WIDTH * 16, (HEIGHT + 10) * 16);
        StdDraw.clear(Color.BLACK);
        StdDraw.show();
        StdDraw.pause(100);

        showMenu();

        String stringSeed = "";
        StdDraw.clear(Color.black);

        MapGenerator mapgen = new MapGenerator();

        if (StdDraw.hasNextKeyTyped()) {
            char keyPress = StdDraw.nextKeyTyped();
            if (keyPress == 'n' || keyPress == 'N') {
                do {
                    StdDraw.enableDoubleBuffering();
                    while (true) {
                        StdDraw.clear(Color.black);
                        StdDraw.text(WIDTH / 2, 35, "Enter yo digits bruh: ");
                        StdDraw.text(WIDTH / 2, 30, "then press 's' to launch");
                        if (StdDraw.hasNextKeyTyped()) {
                            keyPress = StdDraw.nextKeyTyped();
                            if (keyPress == 's' || keyPress == 'S') {
                                break;
                            }
                            stringSeed += keyPress;
                        }
                        StdDraw.text(WIDTH / 2, 20, stringSeed);
                        StdDraw.show();
                    }
                } while (stringSeed.equals(""));
                seed = Long.parseLong(stringSeed);
                mapgen.SEED = seed;
                //intro();
                GamePlay gamed = new GamePlay(mapgen);
                gamed.gamePlay();
            } else if (keyPress == 'l') {
                GamePlay gamed = loadGame();
                gamed.gamePlay();
            } else if (keyPress == 'q') {
                quitGame();
            }
        }
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    public static void quitGame() {
        StdDraw.clear(Color.black);
        StdDraw.text(WIDTH / 2 + 5, 25, "Quit and Saving your World!");
        StdDraw.show();
        System.exit(0);
    }


    public TETile[][] playWithInputString(String input) {
        inputString = true;
        MapGenerator mapgen = new MapGenerator();
        TETile[][] finalWorldFrame;
        GamePlay gamed;

        if (input.charAt(0) == 'l') {
            gamed = loadGame();
            char[] inputKeys = (input.substring(1, input.length())).toCharArray();
            gamed.inputKeys = inputKeys;
        } else if (input.charAt(0) == 'n') {
            int indexOfS = input.indexOf('s');
            seed = Long.parseLong(input.substring(1, indexOfS));
            mapgen.SEED = seed;
            char[] inputKeys = (input.substring(indexOfS + 1, input.length())).toCharArray();
            gamed = new GamePlay(mapgen);
            gamed.inputKeys = inputKeys;
        } else {
            gamed = new GamePlay(mapgen);
        }
        gamed.gamePlay();
        Grid map = gamed.worldState;
        finalWorldFrame = map.tiles;
        return finalWorldFrame;
    }

    public void showMenu() {
        do {
            StdDraw.enableDoubleBuffering();
            StdDraw.clear(Color.black);
            Font font = new Font("Serif", Font.BOLD, 45);
            StdDraw.setFont(font);
            StdDraw.setXscale(0, 90);
            StdDraw.setYscale(0, 50);
            StdDraw.setPenColor(Color.YELLOW);
            StdDraw.text(45, 40, "<<<< Cave-Wars >>>>");
            StdDraw.setPenColor(Color.WHITE);
            StdDraw.text(45, 30, "New Game (press n)");
            StdDraw.text(45, 25, "Load Game (press l)");
            StdDraw.text(45, 20, "Quit (press q)");
            StdDraw.show();
            StdDraw.pause(200);
        } while (!StdDraw.hasNextKeyTyped());
    }

    /*public void intro() {
        StdDraw.setPenColor(Color.YELLOW);
        StdDraw.clear(Color.black);
        StdDraw.text(WIDTH/2+5, 25, "A long time ago");
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        StdDraw.text(WIDTH/2+5, 30, "A long time ago");
        StdDraw.text(WIDTH/2+5, 25, "in a cave far,");
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(Color.black);
        StdDraw.text(WIDTH/2+5, 35, "A long time ago");
        StdDraw.text(WIDTH/2+5, 30, "in a cave far,");
        StdDraw.text(WIDTH/2+5, 25, "far away ....");
        StdDraw.show();
        StdDraw.pause(1000);
        int a = 35;
        int b = 30;
        int c = 25;
        while (a <= 65) {
            StdDraw.clear(Color.black);
            StdDraw.text(WIDTH/2+5, a, "A long time ago");
            StdDraw.text(WIDTH/2+5, b, "in a cave far,");
            StdDraw.text(WIDTH/2+5, c, "far away ....");
            StdDraw.show();
            StdDraw.pause(40);
            a += 1;
            b += 1;
            c += 1;
        }
    }*/


}
