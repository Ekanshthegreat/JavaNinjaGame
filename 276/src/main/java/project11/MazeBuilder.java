package project11;

import java.util.Random;
/**
 * MazeBuilder class used to populate the arary with GameObjects.
 * Uses the GameObjectFactory to create the GameObjects.
 * 
 * @author Felmer
 */
public class MazeBuilder {
    private  GameObject[][] maze;
    private  GameObjectFactory factory;
    private int rows =8;
    private int cols = 10;

    private Random random = new Random();
    private boolean[][] visited = new boolean[rows][cols];

    // Change amount of bushes and holes
    private int maxHoles = 5;
    private int maxBushes = 5;
    private int maxKeys = 3;



    /**
     * Constructor for the MazeBuilder class. Instatiates with GameObjectFactory.
     */
    public MazeBuilder(GameObjectFactory factory) {
        this.factory = factory;
    }

    /**
     * Method to build the maze.
     * @param maze the 2D array to be populated with GameObjects.
     */
    public void buildMaze(GameObject[][] maze) {
        this.maze = maze;
        buildMaze();
        generateBushes();
        generateHoles();
        generateItems();

    }

    
    private GameObject[][] buildMaze() {
        // Initialize all cells as walls (0)
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = factory.createObject("wall", j, i);
            }
        }

        // Start DFS from a random cell to create paths
        int startX = random.nextInt(rows);
        int startY = random.nextInt(cols);
        dfs(startX - startX % 2, startY - startY % 2);  // Adjust to even index for symmetry

        return maze;
    }

    private void dfs(int x, int y) {
        maze[x][y] = null;  // Set current cell as a path
        visited[x][y] = true;

        // Define movement directions (up, down, left, right)
        int[][] directions = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        shuffleArray(directions);  // Randomize directions for better maze structure

        for (int[] dir : directions) {
            int newX = x + dir[0] * 2;
            int newY = y + dir[1] * 2;

            // Check if the new cell is within bounds and not visited
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !visited[newX][newY]) {
                // Clear the wall between the current cell and the new cell
                maze[x + dir[0]][y + dir[1]] = null;
                dfs(newX, newY);  // Recursively visit the new cell
            }
        }
    }

    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    


    private void generateItems(){
        //Randomly choose null spots to be keys
        int keyCount = 0;
        int x,y;
        // Randomly choose null to be keys
        while (keyCount <= maxKeys) {
            x = (int)(Math.random() * (cols - 2)) + 1;
            y= (int)(Math.random() * (rows - 2)) + 1;
            if (maze[y][x] == null) {
                maze[y][x] = factory.createObject("key", x, y);
                keyCount++;
            }
        }
    }


    private void generateHoles(){
        // Randomly choose inner walls to be holes
        int holeCount = 0;

        while (holeCount < maxHoles) {
            // Choose y avoiding the outer walls
            int y = (int)(Math.random() * (rows - 2)) + 1;
            // Choose x avoiding the outer walls
            int x = (int)(Math.random() * (cols - 2)) + 1;


            if (maze[y][x] != null && maze[y][x].typeId == 6) { // Assuming typeId 6 indicates a wall
                maze[y][x] = factory.createObject("hole", x, y);
                holeCount++;
            }
        }
    }

    private void generateBushes(){
        // Randomly choose inner walls to be bushes
        int bushCount = 0;

        while (bushCount < maxBushes) {
            // Choose y avoiding the outer walls
            int y = (int)(Math.random() * (rows - 2)) + 1;
            // Choose x avoiding the outer walls
            int x = (int)(Math.random() * (cols - 2)) + 1;

            if (maze[y][x] != null && maze[y][x].typeId == 6) { // Assuming typeId 6 indicates a wall
                maze[y][x] = factory.createObject("bush", x, y);
                bushCount++;
            }
        }
    }

}
