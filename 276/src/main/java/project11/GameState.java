package project11;

import java.util.ArrayList;

/**
 * Represents the game state, including player and enemy positions.
 */
public class GameState {
    private Player player;
    private ArrayList<Enemy> enemies; // All active enemies
    private GameObject[][] gameObjects; // All game objects in the game
    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private EnemyGenerator enemyGenerator; // Reference to EnemyGenerator

    public GameState() {
        int width = GamePanel.getPlayColumns();
        int height = GamePanel.getPlayRows();
        this.player = new Player(0, height / 2, 5);
        this.enemies = new ArrayList<>();
        this.gameObjects = new GameObject[height][width];
        this.enemyGenerator = new EnemyGenerator(player); // Initialize enemy generator

        // Populate gameObjects array
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.gameObjects[y][x] = new Ground(x, y);
            }
        }

        // Spawn player
        this.gameObjects[height / 2][0] = player;

        // Initialize items and enemies
        initializeItemsAndEnemies();
    }

    private void initializeItemsAndEnemies() {
        int maxX = gameObjects[0].length; // Maximum x boundary
        int maxY = gameObjects.length;    // Maximum y boundary

        // Add three mandatory items
        for (int i = 0; i < 3; i++) {
            int x = Math.min(i + 2, maxX - 1);
            int y = Math.min(i + 2, maxY - 1);
            Item item = (Item) gameObjectFactory.createObject("mandatoryItem", x, y);
            gameObjects[y][x] = item;
        }

        // Add one bonus item
        int bonusX = Math.min(5, maxX - 1);
        int bonusY = Math.min(5, maxY - 1);
        Item bonusItem = (Item) gameObjectFactory.createObject("bonusItem", bonusX, bonusY);
        gameObjects[bonusY][bonusX] = bonusItem;

        // Add five holes
        for (int i = 0; i < 5; i++) {
            int x = Math.min(i + 3, maxX - 1);
            int y = Math.min(i + 1, maxY - 1);
            GameObject hole = gameObjectFactory.createObject("hole", x, y);
            gameObjects[y][x] = hole;
        }

        // Add two initial enemies using EnemyGenerator
        for (int i = 0; i < 2; i++) {
            Enemy enemy = enemyGenerator.createEnemy();
            int x = Math.min(enemy.getX(), maxX - 1);
            int y = Math.min(enemy.getY(), maxY - 1);
            gameObjects[y][x] = enemy;
        }
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
            return; // does nothing if there is a wall
        }

        // Update the gameObjects array
        gameObjects[player.getY()][player.getX()] = new Ground(player.getX(), player.getY()); // Clear old position
        player.setX(newX);
        player.setY(newY);
        gameObjects[player.getY()][player.getX()] = player; // Set new position

        // Update enemy positions
        enemyGenerator.updateEnemies();
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject[][] getGameObjects() {
        return gameObjects;
    }
}
