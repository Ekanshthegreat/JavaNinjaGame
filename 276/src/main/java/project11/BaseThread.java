package project11;

/**
 * BaseThread class that defines the structure of a thread
 */
public abstract class BaseThread implements Runnable {
    protected volatile boolean running = true;
    private int SLEEP_TIME = 1000;

    public void stopThread() {
        running = false;
    }

    public abstract void tick();

    @Override
    public void run() {
        while (running) {
            tick();
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }
    }
}
