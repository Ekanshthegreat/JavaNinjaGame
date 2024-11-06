package project11;

import java.util.ArrayList;
import java.util.Random;

public class GameState {
    private Player player;
    private ArrayList<Enemy> enemies;
    private CellArray cellArray;
    private GameObjectFactory gameObjectFactory = new GameObjectFactory();
    private EnemyGenerator enemyGenerator;
    private MazeBuilder mazeBuilder;
    private Random random = new Random();

    private int collectedItems = 0;
    private final int totalItems = 3; // Assuming three mandatory items for the player to collect

    public GameState() {
        int width = GamePanel.getPlayColumns();
        int height = GamePanel.getPlayRows();
        this.player = new Player(0, height / 2, 5);
        this.enemies = new ArrayList<Enemy>();
        this.cellArray = new CellArray(width, height);
        this.enemyGenerator = new EnemyGenerator(player);
        this.mazeBuilder = new MazeBuilder(gameObjectFactory);

        initializeMaze();
        cellArray.setGameObject(0, height / 2, player);
        initializeItemsAndEnemies();
    }

    private void initializeMaze() {
        GameObject[][] mazeGrid = new GameObject[cellArray.getHeight()][cellArray.getWidth()];
        mazeBuilder.buildMaze(mazeGrid);

        for (int y = 0; y < mazeGrid.length; y++) {
            for (int x = 0; x < mazeGrid[y].length; x++) {
                GameObject obj = mazeGrid[y][x];
                if (obj != null) {
                    cellArray.setGameObject(x, y, obj);
                }
            }
        }
    }

    private void initializeItemsAndEnemies() {
        int maxX = cellArray.getWidth();
        int maxY = cellArray.getHeight();
    
        // this code doesnt even run

        // for (int i = 0; i < totalItems; i++) {
        //     placeObjectRandomly("mandatoryitem", maxX, maxY);
        // }
        
        // placeObjectRandomly("bonusitem", maxX, maxY);
    
        // for (int i = 0; i < 5; i++) {
        //     placeObjectRandomly("hole", maxX, maxY);
        // }
    
        for (int i = 0; i < 2; i++) {
            Enemy enemy = enemyGenerator.createEnemy();
            int x, y;
            do {
                x = random.nextInt(maxX);
                y = random.nextInt(maxY);
            } while (cellArray.getCell(x, y).isOccupied());
            cellArray.setGameObject(x, y, enemy);
        }
    }

    private void placeObjectRandomly(String objectType, int maxX, int maxY) {
        int x, y;
        do {
            x = random.nextInt(maxX);
            y = random.nextInt(maxY);
        } while (cellArray.getCell(x, y).isOccupied());

        GameObject obj = gameObjectFactory.createObject(objectType, x, y);
        // cellArray.setGameObject(x, y, obj); // dont need this, it makes double the amount of holes and they dont work properly
    }

    public void movePlayer(boolean up, boolean down, boolean left, boolean right) {
        int newX = player.getX();
        int newY = player.getY();
        
        if (up && newY > 0) newY--;
        if (down && newY < cellArray.getHeight() - 1) newY++;
        if (left && newX > 0) newX--;
        if (right && newX < cellArray.getWidth() - 1) newX++;
        
        // Interaction with objects
        Cell targetCell = cellArray.getCell(newX, newY);
        if (targetCell.isOccupied()) {
            GameObject obj = targetCell.getPrimaryObject();
            System.out.println("type id=" + obj.getTypeId());
    
            // check object types
            if (obj.typeId == 2) { // Hole
                player.takeDamage(10);
                System.out.println("Player stepped on a hole! Health: " + player.getScore());
            } else if (obj.typeId == 8) { // Mandatory Item
                collectedItems++;
                player.increaseScore(((MandatoryItem) obj).getScore());
                System.out.println("Collected item! Items collected: " + collectedItems + "/" + totalItems);
                targetCell.removeGameObject(obj);
            } else if (obj.isSolid()) {
                System.out.println("Player encountered a solid object!");
                return; // Don't move if a solid object (e.g., wall) is encountered
            }
        }
    
        cellArray.clearGameObject(player.getX(), player.getY());
        player.setX(newX);
        player.setY(newY);
        cellArray.setGameObject(newX, newY, player);
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

    public void updateEnemies() {
        // enemyGenerator.updateEnemies();
        for (Enemy enemy : enemies) {
            cellArray.clearGameObject(enemy.getX(), enemy.getY());
            enemy.moveTowardsPlayer(player);
            cellArray.setGameObject(enemy.getX(), enemy.getY(), enemy);
        }
    }    

    public GameObject[][] getGameObjects() {
        int height = cellArray.getHeight();
        int width = cellArray.getWidth();
        GameObject[][] objects = new GameObject[height][width];
    
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                objects[y][x] = cellArray.getGameObject(x, y);
            }
        }
        return objects;
    }

}
