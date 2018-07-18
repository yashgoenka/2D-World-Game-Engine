package byog.Core;

import java.io.Serializable;

public class Position implements Serializable {

    int x;
    int y;

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    public Position(int xin, int yin) {
        this.x = xin;
        this.y = yin;
    }
}
