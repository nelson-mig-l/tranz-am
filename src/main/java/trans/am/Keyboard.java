package trans.am;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    boolean left, right, up, down;

    @Override
    public void keyTyped(final KeyEvent keyEvent) {
        System.out.println("keyTyped");
    }

    @Override
    public void keyPressed(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                right = true;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                down = true;
                break;
            case KeyEvent.VK_ESCAPE:
            case KeyEvent.VK_Q:
                System.exit(0);
        }
    }

    @Override
    public void keyReleased(final KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                down = false;
                break;
        }
    }
}
