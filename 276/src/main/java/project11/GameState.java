package project11;

import java.util.concurrent.locks.ReentrantLock;

import java.util.ArrayList;

/**
 * Represents the game state, including player and enemy positions.
 */
public class GameState {
    private Player player; // player character
    private ArrayList<Enemy> enemies; // active enemies
    private GameObject[][] gameObjects; // board data

    // replace with player stuff later
    private static final int MOVE_DISTANCE = 16 * 3;
    private int playerX = 0;
    private int playerY = 0;

    // private final ReentrantLock lock = new ReentrantLock();

    public GameState() {
        this.player = new Player(0, 0, 5);
        this.enemies = new ArrayList<>();
        
        // Initialize gameObjects array with desired dimensions
        int width = 20; // Set this based on the game width
        int height = 15; // Set this based on the game height
        this.gameObjects = new GameObject[height][width];
        
        // Populate `gameObjects` with initial objects or leave empty for now
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.gameObjects[y][x] = new Ground(x,y); // Example object; replace as needed
            }
        }
    }

    // Update player position based on input
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        if (up) player.setY(player.getY() - GamePanel.getTileSize());
        if (down) player.setY(player.getY() + GamePanel.getTileSize());
        if (left) player.setX(player.getX() - GamePanel.getTileSize());
        if (right) player.setX(player.getX() + GamePanel.getTileSize());
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject[][] getGameObjects() {
        return gameObjects;
    }

}
