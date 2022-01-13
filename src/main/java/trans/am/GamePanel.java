package trans.am;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    private final Game game;

    GamePanel(Game game, final Keyboard keyboard) {
        this.game = game;
        this.setSize(new Dimension(new Dimension(800, 600)));
        this.addKeyListener(keyboard);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        setRequestFocusEnabled(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        game.draw(g);
    }

    @Override
    public void run() {
        while (true) {
            game.update();
            this.repaint();
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
