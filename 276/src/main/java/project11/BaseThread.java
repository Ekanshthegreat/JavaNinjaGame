package project11;

/**
 * Thread class for running a single thread 
 */
public class BaseThread implements Runnable {
    // Time constants
    private static final int TOTAL_CYCLE_TIME = 1000;
    private static final int INPUT_TIME = 500;

    // Local variables
    private GameState gameState;
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Thread thread;

    /**
     * Initializes Thread with passed objects
     * @param gamePanel GamePanel object
     * @param keyHandler KeyHandler object
     */
    public BaseThread(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.gameState = gamePanel.gameState;
        this.keyHandler = keyHandler;
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Thread constantly runs the run() function
     */
    public void run() {
        while (thread != null) {
            long startTime = System.currentTimeMillis();

            // Take input for INPUT_TIME
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

            // Move enemies according go the player's position
            // moveEnemies();

            // Render the game state
            render();

            // Sleep for remainder 
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
     * Handle input local function
     */
    private void handleInput() {
        gameState.movePlayer(keyHandler.up, keyHandler.down, keyHandler.left, keyHandler.right);
        keyHandler.resetInput(); 
    }

    /**
     * Handle enemy movement local function
     */
    private void moveEnemies(){
        gameState.updateEnemies();
    }
    
    /**
     * Handle rendering local function
     */
    private void render() {
        gamePanel.render();
    }
}
