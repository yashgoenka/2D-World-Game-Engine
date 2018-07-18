package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class ExecTest {

    private static final int hexSize = 3;
    private static final int tesselationSize = 3;
    private static final long SEED = 21;
    private static final Random RANDOM = new Random(SEED);
    private static final int WIDTH = 60;
    private static final int HEIGHT = 40;
    private static final int xOff = 0;
    private static final int yOff = 0;
    private static int posX = 15;
    private static int posY = 10;

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT, xOff, yOff);
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position test = new Position(posX, posY);
        tesselate(world, test, tesselationSize, Tileset.SAND);
        ter.renderFrame(world);
    }

    public static void tesselate(TETile[][] world, Position p, int s, TETile t) {

        for (int xi = 0; xi < (2 * s) - 1; xi += 1) {
            int thisColX = p.x + (xi) * (2 * s) - (1 * xi);
            int yColStart = p.y + (s * colOffset(s, xi));
            Position colStartP = new Position(thisColX, yColStart);
            int colHeight = colHeight(s, xi);
            addCol(world, colStartP, colHeight, t);
        }


    }

    public static int colHeight(int h, int i) {
        int effectiveI = i;
        if (i >= h) {
            effectiveI = 2 * h - 2 - effectiveI;
        }
        return h + effectiveI;
    }

    public static int colOffset(int h, int i) {
        int effectiveI = i;
        if (i >= h) {
            effectiveI = 2 * h - 2 - effectiveI;
        }
        return -effectiveI;
    }

    public static void addCol(TETile[][] world, Position p, int height, TETile t) {
        for (int xi = 0; xi < height; xi += 1) {
            int xCoord = p.x;
            int yCoord = p.y + (xi * hexSize * 2);
            Position temp = new Position(xCoord, yCoord);
            HexWorld.addHexagon(world, temp, hexSize, randomTile());
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.GRASS;
            case 3:
                return Tileset.SAND;
            case 4:
                return Tileset.MOUNTAIN;
            case 5:
                return Tileset.FLOOR;
            default:
                return Tileset.WATER;
        }
    }
}
