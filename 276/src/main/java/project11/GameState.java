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
    private MazeBuilder mazeBuilder;
    private Random random = new Random();

    private int collectedItems = 0;
    private int bonusItem = 0;
    private int chestX = 0;
    private int chestY = 0;

    // Map to track the original objects under the enemies
    private Map<String, GameObject> originalObjects = new HashMap<>();

    /**
     * GameState Constructor
     */
    public GameState() {
        int width = Constants.getPlayColumns();
        int height = Constants.getPlayRows();
        this.player = new Player(0, height / 2);
        int width = Constants.getPlayColumns();
        int height = Constants.getPlayRows();
        this.player = new Player(0, height / 2);
        this.enemies = new ArrayList<>();
        this.gameBoard = new GameObject[height][width];
        // this.enemyGenerator = new EnemyGenerator(player);
        this.mazeBuilder = new MazeBuilder(gameObjectFactory);

        initializeMaze();
        gameBoard[height / 2][0] = player;
        initializeItemsAndEnemies();
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
            originalObjects.put(key, obj != null ? obj : gameObjectFactory.createObject(1, x, y));
            originalObjects.put(key, obj != null ? obj : gameObjectFactory.createObject(1, x, y));
        }
    }
    
    /**
     * Set the difficulty of the game
     * @param difficulty String representing the difficulty level (easy, medium, hard)
     */
    public void setDifficulty(int difficulty) {
        switch (difficulty) {
            case 0:
                updateSamuraiDamage(25);
                break;
            case 1:
                updateSamuraiDamage(35);
                break;
            case 2:
                updateSamuraiDamage(35);
                spawnAdditionalSamurai(3);
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
    private void spawnAdditionalSamurai(int count) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = random.nextInt(gameBoard[0].length);
                y = random.nextInt(gameBoard.length);
            } while (isOccupied(x, y) || isEnemyAt(x, y));
            Enemy enemy = new Samurai(x, y);
            
            Enemy enemy = new Samurai(x, y);
            
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
     * Items and Enemies Constructor
     */
    private void initializeItemsAndEnemies() {
        int maxX = gameBoard[0].length;
        int maxY = gameBoard.length;

        for (int i = 0; i < 2; i++) {
            int x, y;
            do {
                x = random.nextInt(maxX);
                y = random.nextInt(maxY);
            } while (isOccupied(x, y) || isEnemyAt(x, y));
            Enemy enemy = new Samurai(x, y);
            Enemy enemy = new Samurai(x, y);
            
            saveOriginalCellContent(x, y, gameBoard[y][x]);
            gameBoard[y][x] = enemy;
            enemies.add(enemy);
            System.out.println("Placed enemy within bounds at (" + x + ", " + y + ")");
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
        for (Enemy enemy : enemies) {
        for (Enemy enemy : enemies) {
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
    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        // Current position
        int newX = player.getX();
        int newY = player.getY();

        // Update new position
        if (up && newY > 0) newY--;
        if (down && newY < gameBoard.length - 1) newY++;
        if (left && newX > 0) newX--;
        if (right && newX < gameBoard[0].length - 1) newX++;

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
                System.out.println("Collected item! Items collected: " + collectedItems + "/" + Constants.getTotalItems());
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY()); // Remove item after collecting
                System.out.println("Collected item! Items collected: " + collectedItems + "/" + Constants.getTotalItems());
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY()); // Remove item after collecting
            } else if (typeId == 3) { // Bonus item
                bonusItem++;
                player.increaseScore(((BonusItem) targetObject).getScore());
                System.out.println("COLLECTED BONUS ITEM!! Items collected: " + collectedItems + "/" + Constants.getTotalItems());
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY()); // Remove item after collecting
                System.out.println("COLLECTED BONUS ITEM!! Items collected: " + collectedItems + "/" + Constants.getTotalItems());
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY()); // Remove item after collecting
            } else if (typeId == 9) { // Chest/End
                if (collectedItems >= Constants.getTotalItems()) {
                if (collectedItems >= Constants.getTotalItems()) {
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
        gameBoard[player.getY()][player.getX()] = new Ground(player.getX(), player.getY());
        gameBoard[player.getY()][player.getX()] = new Ground(player.getX(), player.getY());

        // Redraw Chest/End if player walked over with not enough keys
        if (collectedItems < Constants.getTotalItems()) {
            gameBoard[chestY][chestX] = new End(chestX, chestY);
        if (collectedItems < Constants.getTotalItems()) {
            gameBoard[chestY][chestX] = new End(chestX, chestY);
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

    /**
     * Get the player object
     * @return Player object
     */
    public int getScore() {
        return player.getScore();
    }
    /**
     * Get the number of collected items
     * @return Number of collected items
     */
    public int getCollectedItems() {
        return collectedItems;
    }
    /**
     * Get the number of bonus items collected
     * @return Number of bonus items collected
     */
    public int getBonusItem() {
        return bonusItem;
    }
    /**
     * Get the game board
     * @return GameObject[][] gameBoard
     */
    public GameObject[][] getGameObjects() {
        return gameBoard;
    }

    /**
     * Remove an enemy from the game
     * @param enemy Enemy to remove
     */
    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        setGround(enemy.getX(), enemy.getY());
    }

    /**
     * Set the ground at a specific position
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void setGround(int x, int y) {
        gameBoard[y][x] = gameObjectFactory.createObject(1, x, y);
        gameBoard[y][x] = gameObjectFactory.createObject(1, x, y);
    }
}
