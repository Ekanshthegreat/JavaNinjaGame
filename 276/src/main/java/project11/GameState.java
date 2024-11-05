package project11;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState {
    private Player player;
    private ArrayList<Enemy> enemies;
    private CellArray cellArray;
    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private EnemyGenerator enemyGenerator;
    private Random random = new Random();

    public GameState() {
        int width = GamePanel.getPlayColumns();
        int height = GamePanel.getPlayRows();
        this.player = new Player(0, height / 2, 5);
        this.enemies = new ArrayList<>();
        this.cellArray = new CellArray(width, height);
        this.enemyGenerator = new EnemyGenerator(player);

        // Place player in the initial cell
        cellArray.setGameObject(0, height / 2, player);

        // Initialize items and enemies
        initializeItemsAndEnemies();
    }

    private void initializeItemsAndEnemies() {
        int maxX = cellArray.getWidth();
        int maxY = cellArray.getHeight();
    
        // Add three mandatory items
        for (int i = 0; i < 3; i++) {
            placeObjectRandomly("mandatoryitem", maxX, maxY);
        }
    
        // Add one bonus item
        placeObjectRandomly("bonusitem", maxX, maxY);
    
        // Add five holes
        for (int i = 0; i < 5; i++) {
            placeObjectRandomly("hole", maxX, maxY);
        }
    
        // Add two initial enemies using EnemyGenerator
        for (int i = 0; i < 2; i++) {
            Enemy enemy = enemyGenerator.createEnemy();
            int x, y;
            do {
                x = random.nextInt(maxX);
                y = random.nextInt(maxY);
            } while (cellArray.getCell(x, y).isOccupied());
            cellArray.setGameObject(x, y, enemy);
            System.out.println("Placed enemy at (" + x + ", " + y + ")");
        }
    }

    // Helper method to place a game object randomly, checking if cell is occupied
    private void placeObjectRandomly(String objectType, int maxX, int maxY) {
        int x, y;
        do {
            x = random.nextInt(maxX);
            y = random.nextInt(maxY);
        } while (cellArray.getCell(x, y).isOccupied()); // Repeat until an unoccupied cell is found

        GameObject obj = gameObjectFactory.createObject(objectType, x, y);
        cellArray.setGameObject(x, y, obj);
        System.out.println("Placed " + objectType + " at (" + x + ", " + y + ")");
    }
    

    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int newX = player.getX();
        int newY = player.getY();
        
        if (up && newY > 0) newY--;           // Move up (decrease Y)
        if (down && newY < cellArray.getHeight() - 1) newY++; // Move down (increase Y)
        if (left && newX > 0) newX--;         // Move left (decrease X)
        if (right && newX < cellArray.getWidth() - 1) newX++; // Move right (increase X)
        
        System.out.println("Input direction - Up: " + up + ", Down: " + down + ", Left: " + left + ", Right: " + right);
        System.out.println("Moving player from (" + player.getX() + ", " + player.getY() + ") to (" + newX + ", " + newY + ")");
    
        Cell targetCell = cellArray.getCell(newX, newY);
        if (targetCell.isOccupied()) {
            System.out.println("Blocked by object at (" + newX + ", " + newY + ")");
            return;
        }
    
        cellArray.clearGameObject(player.getX(), player.getY());
        player.setX(newX);
        player.setY(newY);
        cellArray.setGameObject(newX, newY, player);
    
        System.out.println("Player moved to (" + player.getX() + ", " + player.getY() + ")");
    }
    
    

    public Player getPlayer() {
        return player;
    }

    public GameObject[][] getGameObjects() {
        int height = cellArray.getHeight();
        int width = cellArray.getWidth();
        GameObject[][] objects = new GameObject[height][width]; // Row-major format (height x width)
    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                objects[y][x] = cellArray.getGameObject(x, y); // Access as (x, y)
            }
        }
        return objects;
    }
    
}
