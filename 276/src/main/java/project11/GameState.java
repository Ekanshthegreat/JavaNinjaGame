package project11;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private Player player;
    private ArrayList<Enemy> enemies;
    private CellArray cellArray; // Using CellArray instead of GameObject[][]
    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private EnemyGenerator enemyGenerator;

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
            int x = Math.min(i + 2, maxX - 1);
            int y = Math.min(i + 2, maxY - 1);
            GameObject mandatoryItem = gameObjectFactory.createObject("mandatoryitem", x, y);
            cellArray.setGameObject(x, y, mandatoryItem);
            System.out.println("Placed mandatory item at (" + x + ", " + y + ")");
        }
    
        // Add one bonus item
        int bonusX = Math.min(5, maxX - 1);
        int bonusY = Math.min(5, maxY - 1);
        GameObject bonusItem = gameObjectFactory.createObject("bonusitem", bonusX, bonusY);
        cellArray.setGameObject(bonusX, bonusY, bonusItem);
        System.out.println("Placed bonus item at (" + bonusX + ", " + bonusY + ")");
    
        // Add five holes
        for (int i = 0; i < 5; i++) {
            int x = Math.min(i + 3, maxX - 1);
            int y = Math.min(i + 1, maxY - 1);
            GameObject hole = gameObjectFactory.createObject("hole", x, y);
            cellArray.setGameObject(x, y, hole);
            System.out.println("Placed hole at (" + x + ", " + y + ")");
        }
    
        // Add two initial enemies using EnemyGenerator
        for (int i = 0; i < 2; i++) {
            Enemy enemy = enemyGenerator.createEnemy();
            int x = Math.min(enemy.getX(), maxX - 1);
            int y = Math.min(enemy.getY(), maxY - 1);
            cellArray.setGameObject(x, y, enemy);
            System.out.println("Placed enemy at (" + x + ", " + y + ")");
        }
    }
    

    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int newX = player.getX();
        int newY = player.getY();

        if (up && newY > 0) newY--;
        if (down && newY < GamePanel.getPlayRows() - 1) newY++;
        if (left && newX > 0) newX--;
        if (right && newX < GamePanel.getPlayColumns() - 1) newX++;

        Cell targetCell = cellArray.getCell(newX, newY);
        if (targetCell.isOccupied()) {
            System.out.println("Can't move because there's a wall or object");
            return;
        }

        cellArray.clearGameObject(player.getX(), player.getY());
        player.setX(newX);
        player.setY(newY);
        cellArray.setGameObject(newX, newY, player);

        enemyGenerator.updateEnemies();
    }

    public Player getPlayer() {
        return player;
    }

    public GameObject[][] getGameObjects() {
        GameObject[][] objects = new GameObject[cellArray.getWidth()][cellArray.getHeight()];
        for (int x = 0; x < cellArray.getWidth(); x++) {
            for (int y = 0; y < cellArray.getHeight(); y++) {
                objects[x][y] = cellArray.getGameObject(x, y);
            }
        }
        return objects;
    }
}
