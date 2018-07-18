package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private static Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
            "You got this!", "You're a star!", "Go Bears!",
            "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        /*if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }*/

        long seed = 2389472;//Long.parseLong(args[0]);
        MemoryGameSolution game = new MemoryGameSolution(40, 40, seed);
        game.startGame();
    }

    public MemoryGame(int width, int height, long seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        rand = new Random(seed);
    }

    public String generateRandomString(int n) {
        int letterIndex;
        char[] randomString = new char[n];
        for (int i = 0; i < n; i++) {
            letterIndex = rand.nextInt(CHARACTERS.length);
            randomString[i] = CHARACTERS[letterIndex];
        }
        return Arrays.toString(randomString);
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(this.width / 2, this.height / 2, s);
        StdDraw.setPenColor(Color.white);
        StdDraw.show();
    }

    public void flashSequence(String letters) {
        for (int i = 0; i < letters.length(); i++) {
            String letter = Character.toString(letters.charAt(i));
            drawFrame(letter);
            StdDraw.pause(1000);
            StdDraw.clear(Color.BLACK);
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        char[] inputString = new char[n];
        for (int i = 0; i < n; i++) {
            inputString[i] = StdDraw.nextKeyTyped();
            drawFrame(Arrays.toString(inputString));
        }
        return Arrays.toString(inputString);
    }

    public void startGame() {
        gameOver = false;
        playerTurn = false;
        round = 1;

        while (!gameOver) {
            playerTurn = false;
            drawFrame("Round " + round + "! Good luck!");
            StdDraw.pause(1500);


            String roundString = generateRandomString(round);
            flashSequence(roundString);

            playerTurn = true;
            String userInput = solicitNCharsInput(round);

            if (!userInput.equals(roundString)) {
                gameOver = true;
                drawFrame("Game Over! Final level: " + round);
            } else {
                drawFrame("Correct, well done!");
                StdDraw.pause(1500);
                round += 1;
            }
        }

    }

}
