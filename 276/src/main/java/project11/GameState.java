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

    // private final ReentrantLock lock = new ReentrantLock();

    public GameState() {;
        int width = GamePanel.getPlayColumns();
        int height = GamePanel.getPlayRows();
        this.player = new Player(0, height/2, 5);
        this.enemies = new ArrayList<>();
        
        // Initialize gameObjects array with desired dimensions
        this.gameObjects = new GameObject[height][width];
        
        // Populate gameObjects array
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.gameObjects[y][x] = new Ground(x,y);
            }
        }
        this.gameObjects[height/2][0] = player;
    }

    // Update player position based on input
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int newX = player.getX();
        int newY = player.getY();

        if (up && newY > 0) newY--;
        if (down && newY < GamePanel.getPlayRows() - 1) newY++;
        if (left && newX > 0) newX--;
        if (right && newX < GamePanel.getPlayColumns() - 1) newX++;

        // Collision detection
        GameObject targetObject = gameObjects[newY][newX];
        if (targetObject != null && targetObject.isSolid()) {
            System.out.println("Can't move because there's a wall");
            return; // Do not move if there's a solid object
        }

        // Update the gameObjects array
        gameObjects[player.getY()][player.getX()] = new Ground(player.getX(), player.getY()); // Clear old position
        player.setX(newX);
        player.setY(newY);
        gameObjects[player.getY()][player.getX()] = player; // Set new position
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject[][] getGameObjects() {
        return gameObjects;
    }

}
