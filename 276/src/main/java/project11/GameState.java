package project11;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private Player player;
    private ArrayList<Enemy> enemies;
    private GameObject[][] gameBoard; // Directly store GameObjects in a 2D array
    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private EnemyGenerator enemyGenerator;
    private MazeBuilder mazeBuilder;
    private Random random = new Random();

    private int collectedItems = 0;
    private int bonusItem = 0;
    private final int totalItems = 3; // Assuming three mandatory items for the player to collect

    public GameState() {
        int width = GamePanel.getPlayColumns();
        int height = GamePanel.getPlayRows();
        this.player = new Player(0, height / 2, 5);
        this.enemies = new ArrayList<>();
        this.gameBoard = new GameObject[height][width];
        this.enemyGenerator = new EnemyGenerator(player);
        this.mazeBuilder = new MazeBuilder(gameObjectFactory);

        initializeMaze();
        gameBoard[height / 2][0] = player; // Place player at start position
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
            gameBoard[y][x] = enemy;
            enemies.add(enemy); // Add enemy to the list for movement tracking
        }
    }

    private boolean isOccupied(int x, int y) {
        return gameBoard[y][x] != null && gameBoard[y][x].isSolid();
    }

    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int newX = player.getX();
        int newY = player.getY();
        
        if (up && newY > 0) newY--;
        if (down && newY < gameBoard.length - 1) newY++;
        if (left && newX > 0) newX--;
        if (right && newX < gameBoard[0].length - 1) newX++;
        
        GameObject targetObject = gameBoard[newY][newX];
        if (targetObject != null) {
            int typeId = targetObject.getTypeId();

            if (typeId == 2) { // Hole
                player.takeDamage(10);
                System.out.println("Player stepped on a hole! Health: " + player.getScore());
            } else if (typeId == 8) { // Mandatory Item
                collectedItems++;
                player.increaseScore(((MandatoryItem) targetObject).getScore());
                System.out.println("Collected item! Items collected: " + collectedItems + "/" + totalItems);
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY(), false, 1); // Remove item after collecting
            } else if (typeId == 3) { // bonus item
                bonusItem++;
                player.increaseScore(((BonusItem) targetObject).getScore());
                System.out.println("COLLECTED BONUS ITEM!! Items collected: " + collectedItems + "/" + totalItems);
                gameBoard[newY][newX] = new Ground(player.getX(), player.getY(), false, 1); // Remove item after collecting
            } else if (typeId == 9) { // chest
                if (collectedItems >= totalItems) {
                    System.out.println("Congratulations! You've collected all mandatory items and reached the chest.");
                    System.out.println("Final Score: " + player.getScore());
                    System.out.println("You win!");
                    System.exit(0); // Close the game
                } else {
                    System.out.println("You need to collect all mandatory items before reaching the chest.");
                    // gameBoard[newY][newX] = new End(player.getX(), player.getY(), false, 1);
                }
            } else if (targetObject.isSolid()) {
                System.out.println("Player encountered a solid object!");
                return; // Prevent moving into solid object
            }
        }

        gameBoard[player.getY()][player.getX()] = new Ground(player.getX(), player.getY(), false, 1);; // Clear player's current position
        player.setX(newX);
        player.setY(newY);
        gameBoard[newY][newX] = player; // Update player's new position
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

    public int getBonusItem(){
        return bonusItem;
    }

   public void updateEnemies() {
    for (Enemy enemy : enemies) {
        // Clear enemy's current position
        gameBoard[enemy.getY()][enemy.getX()] = null;
        
        // Move the enemy towards the player
        enemy.moveTowardsPlayer(player);

        // Ensure the enemy's new position is within bounds
        int newX = Math.max(0, Math.min(enemy.getX(), gameBoard[0].length - 1));
        int newY = Math.max(0, Math.min(enemy.getY(), gameBoard.length - 1));

        // Update the enemy's coordinates to stay within bounds
        enemy.setX(newX);
        enemy.setY(newY);

        // Set the enemy's new position on the board
        gameBoard[newY][newX] = enemy;
    }
}


    public GameObject[][] getGameObjects() {
        return gameBoard; // Return the entire board with GameObjects
    }
}
