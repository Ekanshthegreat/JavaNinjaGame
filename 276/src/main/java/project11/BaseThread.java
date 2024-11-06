package project11;

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

            // Handle player input
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

            // Move enemies based on player's position and game rules
            moveEnemies();

            // Render the game state
            render();

            // Wait for the remainder of the cycle to maintain game loop timing
            elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < TOTAL_CYCLE_TIME) {
                try {
                    Thread.sleep(TOTAL_CYCLE_TIME - elapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Uncomment for debugging
            System.out.println("Game currently running");
        }
    }

    private void handleInput() {
        System.out.println("Input state - Up: " + keyHandler.up + ", Down: " + keyHandler.down + ", Left: " + keyHandler.left + ", Right: " + keyHandler.right);
        gameState.movePlayer(keyHandler.up, keyHandler.down, keyHandler.left, keyHandler.right);
        keyHandler.resetInput(); 
    }

    private void moveEnemies() {
        System.out.println("Updating enemy movements");
        gameState.updateEnemies(); // Update enemies based on player's position and game rules
    }

    private void render() {
        gamePanel.render();
    }
}
