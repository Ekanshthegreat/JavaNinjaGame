package project11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * GameState to hold current state of game, most of game logic is held here to manipulate GameObject[][] array
 */
public class GameState {
    // Local Variables
    private Player player;
    private ArrayList<Enemy> enemies;
    private GameObject[][] gameBoard;
    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private EnemyGenerator enemyGenerator;
    private MazeBuilder mazeBuilder;
    private Random random = new Random();

    private int collectedItems = 0;
    private int bonusItem = 0;
    private final int totalItems = 3;
    private int chestX = 0;
    private int chestY = 0;

    // Map to track the original objects under the enemies
    private Map<String, GameObject> originalObjects = new HashMap<>();

    /**
     * GameState Constructor
     */
    public GameState() {
        int width = GamePanel.getPlayColumns();
        int height = GamePanel.getPlayRows();
        this.player = new Player(0, height / 2, 5);
        this.enemies = new ArrayList<>();
        this.gameBoard = new GameObject[height][width];
        this.enemyGenerator = new EnemyGenerator(player);
        this.mazeBuilder = new MazeBuilder(gameObjectFactory);

        initializeMaze();
        gameBoard[height / 2][0] = player;
        spawnSamurai(2);
    }

    /**
     * Save the original content of the cell before moving the enemy
     * @param x X coordinate
     * @param y Y coordinate
     * @param obj GameObject to save
     */
    private void saveOriginalCellContent(int x, int y, GameObject obj) {
        String key = x + "," + y;
        if (!originalObjects.containsKey(key) && !(obj instanceof Enemy)) {
            originalObjects.put(key, obj != null ? obj : gameObjectFactory.createObject("ground", x, y));
        }
    }
    
    /**
     * Set the difficulty of the game
     * @param difficulty String representing the difficulty level (easy, medium, hard)
     */
    public void setDifficulty(String difficulty) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                updateSamuraiDamage(25);
                break;
            case "medium":
                updateSamuraiDamage(35);
                break;
            case "hard":
                updateSamuraiDamage(35);
                spawnSamurai(3);
                break;
        }
    }
    
    /**
     * Update the damage of all Samurai enemies
     * @param damage New damage value
     */
    private void updateSamuraiDamage(int damage) {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Samurai) {
                ((Samurai) enemy).setDamage(damage);
            }
        }
    }
    
    /**
     * Spawn additional Samurai enemies
     * @param count Number of Samurai enemies to spawn
     */
    private void spawnSamurai(int count) {
        for (int i = 0; i < count; i++) {
            Enemy enemy = enemyGenerator.createEnemy();
            int x, y;
            do {
                x = random.nextInt(gameBoard[0].length);
                y = random.nextInt(gameBoard.length);
            } while (isOccupied(x, y) || isEnemyAt(x, y));
    
            enemy.setX(x);
            enemy.setY(y);
            saveOriginalCellContent(x, y, gameBoard[y][x]);
            gameBoard[y][x] = enemy;
            enemies.add(enemy);
        }
    }
    
    /**
     * Maze Constructor
     */
    private void initializeMaze() {
        GameObject[][] mazeGrid = new GameObject[gameBoard.length][gameBoard[0].length];
        mazeBuilder.buildMaze(mazeGrid);

        for (int y = 0; y < mazeGrid.length; y++) {
            for (int x = 0; x < mazeGrid[y].length; x++) {
                GameObject obj = mazeGrid[y][x];
                if (obj != null) {
                    gameBoard[y][x] = obj;
                    if (obj.getTypeId() == 9) { // Store the chest position for redrawing later
                        chestX = x;
                        chestY = y;
                    }
                }
            }
        }
    }


    /**
     * Checks if gameBoard[x][y] is occupied
     * @param x X coordinate
     * @param y Y coordinate
     * @return Boolean if occupied
     */
    private boolean isOccupied(int x, int y) {
        return gameBoard[y][x] != null && gameBoard[y][x].isSolid();
    }

    /**
     * Checks if there is an enemy at gameBoard[x][y]
     * @param x X coordinate
     * @param y Y coordinate
     * @return Boolean if enemy is at position
     */
    private boolean isEnemyAt(int x, int y) {
        return gameBoard[y][x] instanceof Enemy;
    }

    /**
     * Update all enemy positions and handle player contact
     */
    public void updateEnemies() {
        for (Enemy enemy : new ArrayList<>(enemies)) {
            // Check if the enemy is adjacent to the player
            if (isAdjacentToPlayer(enemy)) {
                if (enemy instanceof Samurai) {
                    ((Samurai) enemy).attackPlayer(player, this);
                }
                continue; // Skip further movement if the enemy attacked the player
            }
    
            // Restore the original content of the cell before moving the enemy
            int oldX = enemy.getX();
            int oldY = enemy.getY();
            String key = oldX + "," + oldY;
            if (originalObjects.containsKey(key)) {
                gameBoard[oldY][oldX] = originalObjects.get(key);
            }
    
            // Move the enemy towards the player
            if (enemy instanceof Samurai) {
                ((Samurai) enemy).moveTowardsPlayerAvoidingWalls(player, gameBoard);
            }
    
            // Ensure enemy's new position is valid and update the game board
            int newX = enemy.getX();
            int newY = enemy.getY();
    
            // Check if the new position has an enemy already
            if (isEnemyAt(newX, newY)) {
                continue; // Skip if the new position already has another enemy
            }
    
            GameObject targetCell = gameBoard[newY][newX];
            if (targetCell == null || !targetCell.isSolid()) {
                // Save the original content if it's a passable object (e.g., Hole, Mandatory Item, Bonus Item)
                saveOriginalCellContent(newX, newY, targetCell);
                gameBoard[newY][newX] = enemy;
            } else {
                // Revert to the original position if movement is blocked
                enemy.setX(oldX);
                enemy.setY(oldY);
                gameBoard[oldY][oldX] = enemy;
            }
        }
    }
    
    /**
     * Check if the enemy is adjacent to the player
     * @param enemy Enemy to check
     * @return Boolean if enemy is adjacent to player
     */
    private boolean isAdjacentToPlayer(Enemy enemy) {
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();
        int playerX = player.getX();
        int playerY = player.getY();
    
        // Check if the enemy is in any of the 4 neighboring tiles
        return (Math.abs(enemyX - playerX) == 1 && enemyY == playerY) ||
               (Math.abs(enemyY - playerY) == 1 && enemyX == playerX);
    }

    /**
     * Move Player function, deals with most object interactions
     * @param up Boolean for direction
     * @param down Boolean for direction
     * @param left Boolean for direction
     * @param right Boolean for direction
     */
    public void movePlayer(KeyHandler keyhandler) {
        // Current position
        int newX = player.getX();
        int newY = player.getY();

        // Update new position
        if (keyhandler.up && newY > 0) newY--;
        if (keyhandler.down && newY < gameBoard.length - 1) newY++;
        if (keyhandler.left && newX > 0) newX--;
        if (keyhandler.right && newX < gameBoard[0].length - 1) newX++;

        // Check interactions with objects at the new position
        GameObject targetObject = gameBoard[newY][newX];
        if (targetObject != null) {
            int typeId = targetObject.getTypeId();

            if (typeId == 2) { // Hole
                player.takeDamage(10);
                System.out.println("Stepped on a hole! Health: " + player.getScore());
            } else if (typeId == 8) { // Mandatory Item
                collectedItems++;
                player.increaseScore(((MandatoryItem) targetObject).getScore());
                System.out.println("Collected item! Items collected: " + collectedItems + "/" + totalItems);
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY(), false, 1); // Remove item after collecting
            } else if (typeId == 3) { // Bonus item
                bonusItem++;
                player.increaseScore(((BonusItem) targetObject).getScore());
                System.out.println("COLLECTED BONUS ITEM!! Items collected: " + collectedItems + "/" + totalItems);
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY(), false, 1); // Remove item after collecting
            } else if (typeId == 9) { // Chest/End
                if (collectedItems >= totalItems) {
                    System.out.println("Congratulations! You've collected all mandatory items and reached the chest.");
                    System.out.println("Final Score: " + player.getScore());
                    System.out.println("You win!");
                    System.exit(0); // Close the game
                } else {
                    System.out.println("You need to collect all mandatory items before reaching the chest.");
                }
            } else if (targetObject.isSolid()) {
                System.out.println("Can't walk through walls!");
                return; // Prevent moving into solid object
            }
        }

        // Clear player's current position
        gameBoard[player.getY()][player.getX()] = new Ground(player.getX(), player.getY(), false, 1);

        // Redraw Chest/End if player walked over with not enough keys
        if (collectedItems < totalItems) {
            gameBoard[chestY][chestX] = new End(chestX, chestY, false, 9);
        }

        // Close game if score ever goes below 0
        if (player.getScore() <= 0) {
            System.out.println("Score went below 0, you lost!");
            System.exit(0); // Close the game
        }

        // Set new Player position
        player.setX(newX);
        player.setY(newY);
        gameBoard[newY][newX] = player;
    }

    // Getters 
    public int getScore() {
        return player.getScore();
    }

    public int getCollectedItems() {
        return collectedItems;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public int getBonusItem() {
        return bonusItem;
    }

    public GameObject[][] getGameObjects() {
        return gameBoard;
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        setGround(enemy.getX(), enemy.getY());
    }

    public void setGround(int x, int y) {
        gameBoard[y][x] = gameObjectFactory.createObject("ground", x, y);
    }
}
