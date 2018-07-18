package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Grid implements Serializable {

    int width;
    int height;
    Position pos;
    TETile[][] tiles;

    public Grid() {
        width = Game.WIDTH;
        height = Game.HEIGHT;
        pos = new Position(0, 0);
        tiles = new TETile[width][height];
        fillWith(Tileset.NOTHING);
    }


    public void fillWith(TETile t) {
        for (int x = pos.x; x < (pos.x + width); x += 1) {
            for (int y = pos.y; y < (pos.y + height); y += 1) {
                tiles[x][y] = t;
            }
        }
    }

    public int commonTilesCount(Grid b) {
        int tileCount = 0;

        for (int x = pos.x; x < (pos.x + width); x += 1) {
            for (int y = pos.y; y < (pos.y + height); y += 1) {
                if (tiles[x][y] != Tileset.NOTHING && tiles[x][y] == b.tiles[x][y]) {
                    tileCount += 1;
                }
            }
        }
        return tileCount;
    }

    public boolean isNextTo(Grid b) {
        int tileCount = 0;

        for (int x = pos.x; x < (pos.x + width); x += 1) {
            for (int y = pos.y; y < (pos.y + height); y += 1) {
                if (tiles[x][y] != Tileset.NOTHING && tiles[x][y] == b.tiles[x][y]) {
                    tileCount += 1;
                }
            }
        }

        return true;
    }


    public void wallify() {
        for (int x = 0; x < this.width; x += 1) {
            for (int y = 0; y < this.height; y += 1) {
                if (tiles[x][y] == Tileset.FLOOR) {
                    for (int botLeftX = x - 1; botLeftX < x + 2; botLeftX++) {
                        for (int botLeftY = y - 1; botLeftY < y + 2; botLeftY++) {
                            if (tiles[botLeftX][botLeftY] == Tileset.NOTHING) {
                                if (x % 2 == 0 && x % 3 == 0 && y % 2 == 0) {
                                    tiles[botLeftX][botLeftY] = Tileset.SPIKEDWALL;
                                } else {
                                    tiles[botLeftX][botLeftY] = Tileset.WALL;
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}
