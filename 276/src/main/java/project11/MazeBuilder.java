    package project11;

    /**
     * MazeBuilder class used to populate the arary with GameObjects.
     * Uses the GameObjectFactory to create the GameObjects.
     * 
     * @author Felmer
     */
    public class MazeBuilder {
        private  GameObject[][] maze;
        private  GameObjectFactory factory;
        private int rows;
        private int cols;

        // Change amount of bushes and holes
        private int maxHoles = 3;
        private int maxBushes = 5;



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
            this.rows = maze.length;
            this.cols = maze[0].length;
            generateWallMaze(rows, cols);
            generateOuterWalls();
            generateBushes();
            generateHoles();
            generateItems();

        }


        //recursively generate wall maze
        private void generateWallMaze(int x, int y) {
            // Define movement directions (right, down, left, up)
            int[][] dirs = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        
            for (int[] dir : dirs) {
                int newX = x + dir[0];
                int newY = y + dir[1];
        
                // Check bounds and if cell is empty (null)
                if (newX >= 0 && newX < this.rows && newY >= 0 && newY < this.cols && maze[newX][newY] == null) {
                    maze[newX][newY] = factory.createObject("wall", newY, newX);  // Create wall
                    generateWallMaze(newX, newY);  
                }
            }
        }
        



        private void generateOuterWalls(){
            //top and bottom
            for (int i = 0; i < cols; i++) {
                maze[0][i] = factory.createObject("wall",0,i);
                maze[rows-1][i] = factory.createObject("wall" ,rows-1,i);
            }
            //left and right
            for (int i = 0; i < rows; i++) {
                maze[i][0] = factory.createObject("wall",i,0);
                maze[i][cols-1] = factory.createObject("wall",i,cols-1);
            }
        }

        private void generateItems(){

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
