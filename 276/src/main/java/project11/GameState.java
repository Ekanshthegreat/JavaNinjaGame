package project11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * GameState to hold current state of game, most of game logic is held here for GameObject[][] array
 */
public class GameState {
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

    // Track original objects under enemies to restore them later
    private Map<String, GameObject> originalObjects = new HashMap<>();

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
        initializeItemsAndEnemies();
    }

    private void initializeMaze() {
        GameObject[][] mazeGrid = new GameObject[gameBoard.length][gameBoard[0].length];
        mazeBuilder.buildMaze(mazeGrid);

        for (int y = 0; y < mazeGrid.length; y++) {
            for (int x = 0; x < mazeGrid[y].length; x++) {
                GameObject obj = mazeGrid[y][x];
                if (obj != null) {
                    gameBoard[y][x] = obj;
                    if (obj.getTypeId() == 9) { // Chest position
                        chestX = x;
                        chestY = y;
                    }
                }
            }
        }
    }

    private void initializeItemsAndEnemies() {
        int maxX = gameBoard[0].length;
        int maxY = gameBoard.length;

        for (int i = 0; i < 2; i++) {
            Enemy enemy = enemyGenerator.createEnemy();
            int x, y;
            do {
                x = random.nextInt(maxX);
                y = random.nextInt(maxY);
            } while (isOccupied(x, y));
            enemy.setX(x);
            enemy.setY(y);
            saveOriginalObject(x, y, gameBoard[y][x]); // Store the initial underlying object
            gameBoard[y][x] = enemy;
            enemies.add(enemy);
        }
    }

    private boolean isOccupied(int x, int y) {
        return gameBoard[y][x] != null && gameBoard[y][x].isSolid();
    }

    private void saveOriginalObject(int x, int y, GameObject obj) {
        String key = x + "," + y;
        if (!originalObjects.containsKey(key)) {
            originalObjects.put(key, obj); // Save the original object only if not already saved
        }
    }

    public void updateEnemies() {
        for (Enemy enemy : new ArrayList<>(enemies)) {
            if (isAdjacentToPlayer(enemy)) {
                if (enemy instanceof Samurai) {
                    ((Samurai) enemy).attackPlayer(player, this);
                }
                continue;
            }

            int oldX = enemy.getX();
            int oldY = enemy.getY();

            // Restore the original object in the enemy's previous cell
            restorePreviousCell(enemy, oldX, oldY);

            if (enemy instanceof Samurai) {
                ((Samurai) enemy).moveTowardsPlayerAvoidingWalls(player, gameBoard);
            }

            int newX = enemy.getX();
            int newY = enemy.getY();

            saveOriginalObject(newX, newY, gameBoard[newY][newX]); // Save the current target cell before moving
            gameBoard[newY][newX] = enemy;
        }
    }

    private void restorePreviousCell(Enemy enemy, int x, int y) {
        String key = x + "," + y;
        if (originalObjects.containsKey(key)) {
            gameBoard[y][x] = originalObjects.get(key);
        } else {
            gameBoard[y][x] = new Ground(x, y, false, 1); // Default to ground if not found
        }
        originalObjects.remove(key);
    }

    private boolean isPassableObject(GameObject obj) {
        return obj != null && (obj.getTypeId() == 1 || obj.getTypeId() == 2 || obj.getTypeId() == 3 || obj.getTypeId() == 8);
    }

    private boolean isAdjacentToPlayer(Enemy enemy) {
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();
        int playerX = player.getX();
        int playerY = player.getY();

        return (Math.abs(enemyX - playerX) == 1 && enemyY == playerY) ||
               (Math.abs(enemyY - playerY) == 1 && enemyX == playerX);
    }

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
        originalObjects.remove(enemy.getX() + "," + enemy.getY());
    }

    public void setGround(int x, int y) {
        gameBoard[y][x] = new Ground(x, y, false, 1);
    }

    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        // Current position
        int newX = player.getX();
        int newY = player.getY();
        
        // Update new position
        if (up && newY > 0) newY--;
        if (down && newY < gameBoard.length - 1) newY++;
        if (left && newX > 0) newX--;
        if (right && newX < gameBoard[0].length - 1) newX++;
        
        // Check all object moving into interactions
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
        if(player.getScore() <= 0){
            System.out.println("Score went below 0, you lost!");
            System.exit(0); // Close the game
        }

        // Set new Player position
        player.setX(newX);
        player.setY(newY);
        gameBoard[newY][newX] = player;
    }
}
