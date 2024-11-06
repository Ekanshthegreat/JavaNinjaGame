package project11;

public class BaseThread implements Runnable {
    private static final int TOTAL_CYCLE_TIME = 1000; // Increase cycle time to slow down movement
    private static final int INPUT_TIME = 300;

    private GameState gameState;
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Thread thread;
    private int enemyMoveCycle = 0; // Track cycles to control enemy movement frequency

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
            if (enemyMoveCycle % 1 == 0) { // Move enemies every other cycle
                moveEnemies();
            }
            enemyMoveCycle++;

            render();

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

    private void handleInput() {
        gameState.movePlayer(keyHandler.up, keyHandler.down, keyHandler.left, keyHandler.right);
        keyHandler.resetInput(); 
    }

    private void moveEnemies() {
        gameState.updateEnemies();
    }

    private void render() {
        gamePanel.render();
    }
}
