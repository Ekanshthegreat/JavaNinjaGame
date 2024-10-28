package project11;

import java.util.concurrent.locks.ReentrantLock;

import java.util.ArrayList;

/**
 * Represents the game state, including player and enemy positions.
 */
public class GameState {
    private Player player; // player character
    // private ArrayList<Enemy> enemies; // active enemies
    private GameObject[][] gameObjects; // board data
    private int difficulty; // game difficulty

    // replace with player stuff later
    private static final int MOVE_DISTANCE = 16 * 3;
    private int playerX = 0;
    private int playerY = 0;

    // private final ReentrantLock lock = new ReentrantLock();

    public GameState() {
        // this.player = new Player();
        // this.enemies = new ArrayList<>();
    }

    // Update player position based on input
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        if (up) playerY -= MOVE_DISTANCE;
        if (down) playerY += MOVE_DISTANCE;
        if (left) playerX -= MOVE_DISTANCE;
        if (right) playerX += MOVE_DISTANCE;
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

}
