package trans.am;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Car {
    private int angle;
    double speed = 0;
    double fuel = 130;
    double temp = 60;

    private final BufferedImage originalImage;
    private BufferedImage image;
    private final int posX, posY;

    Car(BufferedImage image, int posX, int posY) {
        this.originalImage = image;
        this.setAngle(180);
        this.posX = posX;
        this.posY = posY;
    }

    void update() {
        fuel -= speed / 100.0;
        temp = Utils.limit(temp + ((speed-3)/100), 60, 130);
        if (temp > 120) {
            speed -= 0.1;
        }
    }

    void draw(Graphics g) {
        final int offsetX = image.getWidth() / 2;
        g.drawImage(image, posX - offsetX, posY - offsetX, null);
    }

    int getAngle() {
        return angle;
    }

    void setAngle(int value) {
        if (value != angle) {
            //image = ImageUtils.rotate(originalImage, -Math.toRadians(value));
            image = ImageUtils.rotateImageByDegrees(originalImage, -value);
        }
        angle = value;
    }

}

