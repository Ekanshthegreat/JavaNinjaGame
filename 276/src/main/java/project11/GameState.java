package project11;

import java.util.concurrent.locks.ReentrantLock;

import java.util.ArrayList;

/**
 * Represents the game state, including player and enemy positions.
 */
public class GameState {
    private int playerX;
    private int playerY;
    private ArrayList<Enemy> enemies;

    private final ReentrantLock lock = new ReentrantLock();

    public GameState() {
        this.playerX = 0;
        this.playerY = 0;
        this.enemies = new ArrayList<>();
    }

}
