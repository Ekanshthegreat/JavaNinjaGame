package project11;

/**
 * BaseThread class that defines the structure of a thread
 */
public class BaseThread implements Runnable {
    private int SLEEP_TIME = 1000;
    private GameState gameState; 
    private Thread thread;

    public BaseThread() {
        this.gameState = new GameState();
        this.thread = new Thread(this);
        this.thread.start();
    }

    /**
     * Core game Loop
     */
    public void run() {
        while(thread != null) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Game thread is running");

        }
    }
}
