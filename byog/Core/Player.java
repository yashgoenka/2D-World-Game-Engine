package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Player implements Serializable {

    int health;
    Position pos;
    TETile tile;
    Position prevPos;

    Player() {
        tile = Tileset.PLAYER;
        health = 3;
        pos = new Position();
        prevPos = new Position();
    }

    public Player(TETile player, Grid worldState) {
    }

    public void addPlayer(Grid map, Position xy) {
        pos = xy;
        map.tiles[pos.x][pos.y] = tile;
    }

}
