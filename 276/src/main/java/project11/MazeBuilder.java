package project11;

import java.util.Random;

public class MazeBuilder {
    private GameObject[][] maze;
    private GameObjectFactory factory;
    private int rows = GamePanel.getPlayRows();
    private int cols = GamePanel.getPlayColumns();

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

    public MazeBuilder(GameObjectFactory factory) {
        this.factory = factory;
        this.visited = new boolean[rows][cols];
    }

    public void buildMaze(GameObject[][] maze) {
        this.maze = maze;
        // Initializes the maze with barriers, paths, items.
        generateBarriers();    
        generateHoles();
        generateEnd();
        generateItems();
    }

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

    private void generateEnd() {
        int x = cols - 1;
        int y = rows/2;
        maze[y][x] = factory.createObject("end", x, y);
    }

    
}
