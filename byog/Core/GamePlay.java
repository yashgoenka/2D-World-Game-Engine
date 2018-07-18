package byog.Core;

import byog.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Random;

import static byog.Core.Save.saveGame;


public class GamePlay implements Serializable {


    boolean gameOver = false;
    boolean won = false;
    char key;
    Player yoda;
    Grid worldState;
    MapGenerator mapg;

    char[] inputKeys;

    public GamePlay(MapGenerator mapgen) {
        mapg = mapgen;
        yoda = new Player();
    }

    public void gamePlay() {
        mapg.RANDOM = new Random(mapg.SEED);
        worldState = mapg.makeRandomMap();
        mapg.placeDiamond(worldState);
        if (yoda.pos.x == 0 && yoda.pos.y == 0) {
            yoda.pos = mapg.startingPos(worldState);
        }
        yoda.addPlayer(worldState, yoda.pos);
        if (Game.inputString) {
            for (int i = 0; i < inputKeys.length; i++) {
                if (gameOver) {
                    break;
                }
                key = inputKeys[i];
                yoda.prevPos.x = yoda.pos.x;
                yoda.prevPos.y = yoda.pos.y;

                switch (key) {
                    case 'w':
                        yoda.pos.y = yoda.pos.y + 1;
                        move();
                        break;
                    case 'a':
                        yoda.pos.x = yoda.pos.x - 1;
                        move();
                        break;
                    case 's':
                        yoda.pos.y = yoda.pos.y - 1;
                        move();
                        break;
                    case 'd':
                        yoda.pos.x = yoda.pos.x + 1;
                        move();
                        break;
                    case ':':
                        if (inputKeys[i + 1] == 'q') {
                            saveGame(this);
                        }
                        break;
                    default:
                }
            }

        }
    }

    void showMessage(String message, String second, Color color) {
        StdDraw.pause(500);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(Color.black);
        Font font = new Font("Serif", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, 90);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenColor(color);
        StdDraw.text(45, 40, message);
        StdDraw.text(45, 30, second);
        StdDraw.show();
        StdDraw.pause(1000);
    }

    public void clear() {
        worldState.tiles[yoda.prevPos.x][yoda.prevPos.y] = Tileset.FLOOR;
    }

    public void move() {
        if (worldState.tiles[yoda.pos.x][yoda.pos.y] == Tileset.WALL) {
            yoda.pos.x = yoda.prevPos.x;
            yoda.pos.y = yoda.prevPos.y;
        } else if (worldState.tiles[yoda.pos.x][yoda.pos.y] == Tileset.SPIKEDWALL) {
            yoda.pos.x = yoda.prevPos.x;
            yoda.pos.y = yoda.prevPos.y;
        } else if (worldState.tiles[yoda.pos.x][yoda.pos.y] == Tileset.DIAMOND) {
            gameOver = true;
            won = true;
        }
        clear();
        yoda.addPlayer(worldState, yoda.pos);
    }
}
