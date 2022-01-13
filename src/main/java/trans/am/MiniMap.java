package trans.am;

import static trans.am.Map.TILE_SIZE;

import java.awt.*;

public class MiniMap {

    private final Map map;
    private int count = 0;

    MiniMap(final Map map) {
        this.map = map;
    }

    void update() {
        count++;
    }

    void draw(final Graphics g) {
        if (count % 10 > 5) {
            g.setColor(Color.LIGHT_GRAY);
        } else {
            g.setColor(Color.DARK_GRAY);
        }
        g.fillRect(5 + (int)(map.getX() / TILE_SIZE), 198 + (int)(map.getY() / TILE_SIZE), 4, 4);
    }

}
