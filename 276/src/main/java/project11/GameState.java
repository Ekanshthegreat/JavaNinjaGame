package project11;

import java.util.ArrayList;
import java.util.Random;

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
                    if (obj.getTypeId() == 9) {
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
            gameBoard[y][x] = enemy;
            enemies.add(enemy);
            System.out.println("Placed enemy within bounds at (" + x + ", " + y + ")");
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
        if (targetObject != null && targetObject.isSolid()) {
            System.out.println("Can't move through solid object at (" + newX + ", " + newY + ")");
            return;
        }

        gameBoard[player.getY()][player.getX()] = new Ground(player.getX(), player.getY(), false, 1);
        player.setX(newX);
        player.setY(newY);
        gameBoard[newY][newX] = player;
        System.out.println("Player moved to (" + player.getX() + ", " + player.getY() + ")");
    }

    public void updateEnemies() {
        int maxX = gameBoard[0].length - 1;
        int maxY = gameBoard.length - 1;

        System.out.println("Updating enemy movements");

        for (Enemy enemy : enemies) {
            int oldX = enemy.getX();
            int oldY = enemy.getY();

            // Clear enemy's current position
            if (isWithinBounds(oldX, oldY)) {
                gameBoard[oldY][oldX] = null;
            }

            // Move enemy towards player while avoiding walls
            enemy.moveTowardsPlayer(player);
            int newX = Math.max(0, Math.min(enemy.getX(), maxX));
            int newY = Math.max(0, Math.min(enemy.getY(), maxY));

            // Only move if the target cell is grass (or any other non-solid object)
            if (gameBoard[newY][newX] == null || !gameBoard[newY][newX].isSolid()) {
                enemy.setX(newX);
                enemy.setY(newY);
                gameBoard[newY][newX] = enemy;
            } else {
                // Restore enemy's original position if target cell is blocked
                enemy.setX(oldX);
                enemy.setY(oldY);
                gameBoard[oldY][oldX] = enemy;
                System.out.println("Blocked enemy movement at (" + newX + ", " + newY + ")");
            }
        }
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < gameBoard[0].length && y >= 0 && y < gameBoard.length;
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
}
