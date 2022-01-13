package trans.am;

public class App {
    public static void main(String[] args) {
        final Keyboard keyboard = new Keyboard();
        final Game game = new Game(keyboard);
        final GamePanel gamePanel = new GamePanel(game, keyboard);
        final Window window = new Window(gamePanel, keyboard);
        gamePanel.run();
    }
}
