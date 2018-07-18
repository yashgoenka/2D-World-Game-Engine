package byog.Core;

import byog.TileEngine.Tileset;

import java.io.Serializable;
import java.util.Random;


public class MapGenerator implements Serializable {

    long SEED;
    Random RANDOM;

    private int maxRoomWidth = 8;
    private int maxRoomHeight = 6;

    private int gridWidth = Game.WIDTH;
    private int gridHeight = Game.HEIGHT;

    Grid map;


    private Position randomPos() {
        int x = RandomUtils.uniform(RANDOM, 1, gridWidth - maxRoomWidth);
        int y = RandomUtils.uniform(RANDOM, 1, gridHeight - maxRoomHeight);
        return new Position(x, y);
    }

    public Position startingPos(Grid givenmap) {
        Position pos = randomPos();
        while (givenmap.tiles[pos.x][pos.y] != Tileset.FLOOR) {
            pos = randomPos();
        }
        return pos;
    }

    public void placeDiamond(Grid givenmap) {
        Position diamPos = randomPos();
        while (givenmap.tiles[diamPos.x][diamPos.y] != Tileset.FLOOR) {
            diamPos = randomPos();
        }
        map.tiles[diamPos.x][diamPos.y] = Tileset.DIAMOND;

    }

    /*private Position linkPos(Grid r) {
        int x = RANDOM.nextInt(r.width) + 1 + r.pos.x; // +1?
        int y = RANDOM.nextInt(r.height) + 1 + r.pos.y; // +1?
        return new Position(x, y);
    }*/


    private Room randomRoom() {
        int w = RandomUtils.uniform(RANDOM, 1, maxRoomWidth);
        int h = RandomUtils.uniform(RANDOM, 1, maxRoomHeight);
        return new Room(w, h, randomPos());
    }

    private Room randomHallway() {
        int w = RANDOM.nextInt(maxRoomWidth) + 1;
        int h = RANDOM.nextInt(maxRoomHeight) + 1;
        int shape = RANDOM.nextInt(2);
        switch (shape) {
            case 0:
                return new Room(w, 1, randomPos());
            case 1:
                return new Room(1, h, randomPos());
            default:
        }
        return new Room(w, h, randomPos());
    }


    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x < upper;
    }

    public Grid makeRandomMap() {
        map = new Grid();
        Room r;

        for (int i = 0; i < 100; i++) {
            r = randomRoom();
            if (i > 1) {
                while (!isBetween(map.commonTilesCount(r), 1, 8)) {
                    r.fillWith(Tileset.NOTHING);
                    r.pos = randomPos();
                    r.fillWith(Tileset.FLOOR);
                }
            }
            r.addRoomto(map);
        }
        map.wallify();

        return map;

        /*for (int i = 0; i < 10; i++) {
            if (i == 0) {
                r = randomRoom();
            }else if (i%2==0) {
                r = randomHallway();
            }else {
                r = randomRoom();
            }
            r.pos = randomPos();
            r.fillWith(Tileset.FLOOR);
            while (!isBetween(map.commonTilesCount(r), 1, 9)) {
                r.fillWith(Tileset.NOTHING);
                r.pos = randomPos();
                r.fillWith(Tileset.FLOOR);
            }
            r.addRoomto(map);
        }*/
    }
}
