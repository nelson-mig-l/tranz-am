package trans.am;

import java.awt.image.BufferedImage;

public class Map {

    static final int TILE_SIZE = 50;

    private double x = 100 * TILE_SIZE;
    private double y = 70 * TILE_SIZE;

    private final BufferedImage noiseMap;

    Map(final BufferedImage noiseMap) {
        this.noiseMap = noiseMap;
    }

    void increment(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    int get(int x, int y) {
        return noiseMap.getRGB((int) (this.x / TILE_SIZE) + x, (int) (this.y / TILE_SIZE) + y);
    }

    void set(int x, int y, int color) {
        noiseMap.setRGB((int) (this.x / TILE_SIZE) + x, (int) (this.y / TILE_SIZE) + y, color);
    }

    double getX() {
        return x;
    }

    double getY() {
        return y;
    }
}
