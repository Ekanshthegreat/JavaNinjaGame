package project11;

/**
 * Single thread to run game logic
 */
public class BaseThread implements Runnable {
    private static final int TOTAL_CYCLE_TIME = 500; // Increase cycle time to slow down movement
    private static final int INPUT_TIME = 250;

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
            sleep(elapsed, INPUT_TIME);

            // Move enemies only every few cycles to slow them down
            if (enemyMoveCycle % 2 == 0) { // Move enemies every other cycle
                moveEnemies();
            }
            enemyMoveCycle++;

            render();

            // Finish TOTAL_CYCLE_TIME for consistent game speed
            elapsed = System.currentTimeMillis() - startTime;
            sleep(elapsed, TOTAL_CYCLE_TIME);
        }
    }

    /**
     * Sleep function
     * @param elapsedTime Current time elapsed
     * @param cycleTime Cycle time to sleep until
     */
    private void sleep(long elapsedTime, int cycleTime) {
        if (elapsedTime < cycleTime) {
            try {
                Thread.sleep(cycleTime - elapsedTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handle all input
     */
    private void handleInput() {
        gameState.movePlayer(keyHandler);
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