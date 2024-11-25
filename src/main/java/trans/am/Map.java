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
        int tileX = (int) (this.x / TILE_SIZE);
        int tileY = (int) (this.y / TILE_SIZE);
        int mapX = tileX + x;
        int mapY = tileY + y;
        if (mapX >= 0 && mapY >= 0 && mapX < noiseMap.getWidth() && mapY < noiseMap.getHeight()) {
            try {
                return noiseMap.getRGB(mapX, mapY);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(mapX + ", " + mapY + " " + noiseMap.getWidth() + ", " + noiseMap.getHeight());
                return 0;
            }
        }
        return 0;
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
