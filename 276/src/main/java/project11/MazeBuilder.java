package project11;

import java.util.Random;

/**
 * Class for generating all random positions of walls, items, and enemies
 */
public class MazeBuilder {
    // Local Variables
    private GameObject[][] maze;
    private GameObjectFactory factory;
    private int rows = GamePanel.getPlayRows();
    private int cols = GamePanel.getPlayColumns();

    // Getters
    protected int getRows(){
        return rows;
    }
    protected int getCols(){
        return cols;
    }

    private Random random = new Random();
    private boolean[][] visited;

    private int maxBonusItems = 1;
    private int maxHoles = 10;
    private int maxKeys = 3;

    /**
     * MazeBuilder constructor
     * @param factory GameFactory Object, for creating new objects
     */
    public MazeBuilder(GameObjectFactory factory) {
        this.factory = factory;
        this.visited = new boolean[rows][cols];
    }

    /**
     * BuildMaze calls all generate functions
     * @param maze 2D array of all GameObjects
     */
    public void buildMaze(GameObject[][] maze) {
        this.maze = maze;
        generateBarriers();    
        generateHoles();
        generateEnd();
        generateItems();
    }

    /**
     * Add maze walls game
     * @return Updated 2D array with walls
     */
    private GameObject[][] generateBarriers() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = factory.createObject("barrier", j, i);
                visited[i][j] = false;  // Reset visited array
            }
        }

        int startX = random.nextInt(rows);
        int startY = random.nextInt(cols);
        dfs(startX - startX % 2, startY - startY % 2);  // Start DFS for path generation

        for (int i = 0; i < rows; i++) {
            maze[i][0] = factory.createObject("ground", 0, i);
            maze[i][cols - 1] = factory.createObject("ground", cols - 1, i);
        }

        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if (maze[j][i] == null) {
                    maze[j][i] = factory.createObject("ground", i, j);
                }
            }
        }

        return maze;
    }

    private void dfs(int x, int y) {
        if (visited[x][y]) return;  // Stop recursion if the cell is already visited

        maze[x][y] = factory.createObject("ground", x, y);
        visited[x][y] = true;

        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        shuffleArray(directions);  // Shuffle to randomize path directions

        for (int[] dir : directions) {
            int newX = x + dir[0] * 2;
            int newY = y + dir[1] * 2;

            if (isValid(newX, newY) && !visited[newX][newY]) {
                maze[x + dir[0]][y + dir[1]] = factory.createObject("ground", x + dir[0], y + dir[1]);
                dfs(newX, newY);
            }
        }
    }

    private boolean isValid(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * Add items to maze
     */
    private void generateItems() {
        int keyCount = 0;
        int bonusItemCount = 0;
        while (keyCount < maxKeys || bonusItemCount < maxBonusItems) {
            int x = random.nextInt(cols);
            int y = random.nextInt(rows);
            if (maze[y][x] != null && maze[y][x].getTypeId() == 1) { // Check for ground
                if (keyCount < maxKeys) {
                    maze[y][x] = factory.createObject("mandatoryitem", x, y);
                    keyCount++;
                } else if (bonusItemCount < maxBonusItems) {
                    maze[y][x] = factory.createObject("bonusitem", x, y);
                    bonusItemCount++;
                }
            }
        }
    }

    /**
     * Add holes to maze
     */
    private void generateHoles() {
        int holeCount = 0;
        while (holeCount < maxHoles) {
            int x = random.nextInt(cols);
            int y = random.nextInt(rows);
            if (maze[y][x] != null && maze[y][x].getTypeId() == 6) {
                maze[y][x] = factory.createObject("hole", x, y);
                holeCount++;
            }
        }
    }

    /**
     * Add end square to maze
     */
    private void generateEnd() {
        int x = cols - 1;
        int y = rows/2;
        maze[y][x] = factory.createObject("end", x, y);
    }

}
