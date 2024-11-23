package project11;

/**
 * Single thread to run game logic
 */
public class BaseThread implements Runnable {
    private static final int TOTAL_CYCLE_TIME = 1000; // Increase cycle time to slow down movement
    private static final int INPUT_TIME = 150;

    private GameState gameState;
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Thread thread;
    private int enemyMoveCycle = 0; // Track cycles to control enemy movement frequency

    /**
     * Constructor
     * @param gamePanel GamePanel for drawing
     * @param keyHandler KeyHandler for input
     */
    public BaseThread(GamePanel gamePanel, KeyHandler keyHandler) {
        try { // Check if gamePanel is null
            this.gamePanel = gamePanel;
            this.gameState = gamePanel.gameState;
        } catch (Exception e) {
            throw new NullPointerException("Game Panel is null");
        }
        try{ // Check if keyHandler is null
            this.keyHandler = keyHandler;
        } catch (Exception e) {
            throw new NullPointerException("KeyHandler is null");
        }

        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Run the game loop
     */
    public void run() {
        while (thread != null) {
            long startTime = System.currentTimeMillis();

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

            // Move enemies only every few cycles to slow them down
            if (enemyMoveCycle % 2 == 0) { // Move enemies every other cycle
                moveEnemies();
            }
            enemyMoveCycle++;

            render();

            // Finish TOTAL_CYCLE_TIME for consistent game speed
            elapsed = System.currentTimeMillis() - startTime;
            if (elapsed < TOTAL_CYCLE_TIME) {
                try {
                    Thread.sleep(TOTAL_CYCLE_TIME - elapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Handle all input
     */
    private void handleInput() {
        gameState.movePlayer(keyHandler.up, keyHandler.down, keyHandler.left, keyHandler.right);
        keyHandler.resetInput(); 
    }

    /**
     * Move all enemies
     */
    private void moveEnemies() {
        gameState.updateEnemies();
    }

    /**
     * Render the game
     */
    private void render() {
        gamePanel.render();
    }
}