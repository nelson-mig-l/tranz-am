package trans.am;

import static trans.am.Map.TILE_SIZE;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game {

    private static final Color BOUNDARY_COLOR = Color.WHITE;
    private static final Color FUEL_COLOR = Color.GREEN;
    private static final Color CUPS_COLOR = Color.RED;

    private final Car car;
    private final MiniMap miniMap;
    private final Map map;
    int cups = 0;
    double miles = 0;
    int count = 0;
    int gameStatus = 0;

    private final BufferedImage sidePanel;
    private final BufferedImage boundary;
    private final BufferedImage fuel;
    private final BufferedImage cup;
    private final BufferedImage dot;
    private final BufferedImage grass;

    private final Keyboard keyboard;

    public Game(final Keyboard keyboard) {
        this.keyboard = keyboard;

        map = new Map(ImageUtils.load("noisemap.png"));

        sidePanel = ImageUtils.load("sidepanel.png");
        boundary = ImageUtils.load("boundary.png");
        fuel = ImageUtils.load("fuel.png");
        cup = ImageUtils.load("cup.png");
        dot = ImageUtils.load("dot.png");
        grass = ImageUtils.load("grass.png");

        car = new Car(ImageUtils.load("car.png"), 500, 300);
        miniMap = new MiniMap(map);
    }

    void update() {
        if (gameStatus == 0) {
            checkInput();
            if (car.fuel > 0) {
                final double dx = -car.speed * Math.sin(Math.toRadians(car.getAngle()));
                final double dy = -car.speed * Math.cos(Math.toRadians(car.getAngle()));
                map.increment(dx, dy);
                car.update();
                miles += car.speed / 500.0;
                miniMap.update();
            }
        }
    }

    void draw(Graphics screen) {
        drawMainMap(screen);
        car.draw(screen);
        screen.drawImage(sidePanel, 0, 0, null);
        miniMap.draw(screen);
        screen.setColor(Color.GREEN);
        screen.fillRect(60, 400, Double.valueOf(car.fuel).intValue(), 20);
        screen.fillRect(60, 349, Double.valueOf(car.speed * 26).intValue(), 20);
        screen.setColor(Color.ORANGE);
        screen.fillRect(60, 450, Double.valueOf(car.temp).intValue(), 20);
        screen.setFont(new Font(Font.SERIF, Font.BOLD,  120));
        screen.setColor(Color.WHITE);
        screen.drawString(String.valueOf(cups), 100, 588);
        screen.setFont(new Font(Font.SERIF, Font.BOLD,  25));
        screen.drawString(String.valueOf(count/50), 100, 115); // Elapsed time
        screen.drawString(String.valueOf(Math.round(miles)), 100, 160);
        if (gameStatus == 1) {
            screen.setFont(new Font(Font.SERIF, Font.BOLD,  80));
            screen.drawString("YOU GOT ALL THE CUPS!", 100, 150);
        }
        count++;
    }

    private void drawMainMap(Graphics screen) {
        screen.setColor(Color.WHITE);
        screen.fillRect(200, 0, 600, 600);
        final double xOff = map.getX() % TILE_SIZE;
        final double yOff = map.getY() % TILE_SIZE;
        for (int x = -1; x < 13; x++) {
            for (int y = -1; y < 13; y++) {
                final int rgb = map.get(x, y);
                final Color pixel = new Color(rgb);
                final int xx = (int)(200 + (x * TILE_SIZE) - xOff);
                final int yy = (int)((y * TILE_SIZE) - yOff);
                // BOUNDARY
                if (pixel.equals(BOUNDARY_COLOR)) {
                    screen.drawImage(boundary, xx, yy, null);
                    if (x == 6 && y == 6) {
                        screen.setColor(Color.PINK);
                        screen.drawRect(xx, yy, TILE_SIZE, TILE_SIZE);
                        car.setAngle( (car.getAngle() + 180) % 360 );
                    }
                // FUEL
                } else if (pixel.equals(FUEL_COLOR)) {
                    if (x == 6 && y == 6) {
                        screen.setColor(Color.PINK);
                        screen.drawRect(xx, yy, TILE_SIZE, TILE_SIZE);
                        car.fuel = Math.min(car.fuel + 1, 130);
                    }
                    screen.drawImage(fuel, xx, yy, null);
                // CUPS
                } else if (pixel.equals(CUPS_COLOR)) {
                    if (x == 6 && y == 6) {
                        map.set(x, y, Color.BLACK.getRGB());
                        cups += 1;
                        if (cups == 8) {
                            gameStatus = 1;
                        }
                    } else {
                        screen.drawImage(cup, xx, yy, null);
                    }
                // JUST TERRAIN
                } else {
                    if (pixel.getBlue() > 200) {
                        screen.drawImage(dot, xx, yy, null);
                    }
                    if (pixel.getBlue() < 55) {
                        screen.drawImage(grass, xx, yy, null);
                    }
                }
            }
        }
    }

    private void checkInput() {
        if (keyboard.left) car.setAngle( (car.getAngle() + 5)%360);
        if (keyboard.right) car.setAngle( (car.getAngle() - 5)%360);
        if (keyboard.up) car.speed = Utils.limit(car.speed + 0.1, 0, 5);
        if (keyboard.down) car.speed = Utils.limit(car.speed - 0.1, 0, 5);
        //System.out.println(car.speed + " -> " + car.angle);
    }

}
