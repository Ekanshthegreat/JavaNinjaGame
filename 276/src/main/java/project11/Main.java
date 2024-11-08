package project11;

/**
 * Main class to run the Game.
 */
public class Main{

    /**
     * Main method to run the Game.
     * @param args default arguments
     */
    public static void main(String[] args) {
        System.out.println("Test!");

        GameState gameState = new GameState();
        KeyHandler keyHandler = new KeyHandler();
        GamePanel gamePanel = new GamePanel(gameState, keyHandler);

        Window window = new Window(gamePanel);
        BaseThread gameThread = new BaseThread(gamePanel, keyHandler);

    }
}