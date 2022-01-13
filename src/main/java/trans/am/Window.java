package trans.am;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    Window(final GamePanel panel, Keyboard keyboard) {
        super("Simple GUI");
        this.setSize(new Dimension(new Dimension(800, 600)));
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        centreWindow(this);

        add(panel);

        addKeyListener(keyboard);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.setVisible(true);
    }

    private static void centreWindow(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

}
