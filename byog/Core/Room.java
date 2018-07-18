package byog.Core;

import byog.TileEngine.Tileset;

import java.io.Serializable;

public class Room extends Grid implements Serializable {

    private boolean hall = false;

    public Room(int w, int h) {
        width = w;
        height = h;
        fillWith(Tileset.FLOOR);
    }

    public Room(Grid anotherRoom) {
        width = anotherRoom.width;
        height = anotherRoom.height;
        pos = anotherRoom.pos;
    }


    public Room(int w, int h, Position p) {
        width = w;
        height = h;
        pos = p;
        fillWith(Tileset.FLOOR);
    }

    public boolean hallWay() {
        return true;
    }


    public void addRoomto(Grid map) {
        for (int x = pos.x; x < (pos.x + width); x += 1) {
            for (int y = pos.y; y < (pos.y + height); y += 1) {
                map.tiles[x][y] = tiles[x][y];
            }
        }
    }

}
