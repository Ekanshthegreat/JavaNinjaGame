package project11;

/**
 * BaseThread class to handle the game loop.
 */
public class BaseThread implements Runnable {
    private static final int TOTAL_CYCLE_TIME = 1000; // 1 second
    private static final int INPUT_TIME = 500;        // 500ms input phase

    private GameState gameState; 
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Thread thread;

    public BaseThread(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.gameState = gamePanel.gameState;
        this.keyHandler = keyHandler;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void run() {
        while (thread != null) {
            long startTime = System.currentTimeMillis();

            // Handle input phase (first 500ms)
            handleInput();

            // Wait until the input phase is over
            long elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < INPUT_TIME) {
                try {
                    Thread.sleep(INPUT_TIME - elapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // Render the game state
            render();

            // Wait for the remainder of the cycle
            elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < TOTAL_CYCLE_TIME) {
                try {
                    Thread.sleep(TOTAL_CYCLE_TIME - elapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Game currently running");
        }
    }

    private void handleInput() {
        gameState.movePlayer(keyHandler.up, keyHandler.down, keyHandler.left, keyHandler.right);
        keyHandler.resetInput(); // Reset input for the next cycle
    }

    private void render() {
        gamePanel.render();
    }
}
