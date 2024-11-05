package project11;

/**
 * Main class to run the game
 */
public class Main{
    public static void main(String[] args) {
        System.out.println("Test!");

        // Make all game objects
        GameState gameState = new GameState();
        KeyHandler keyHandler = new KeyHandler();
        GamePanel gamePanel = new GamePanel(gameState, keyHandler);
        Window window = new Window(gamePanel);
        BaseThread gameThread = new BaseThread(gamePanel, keyHandler);

    }
}