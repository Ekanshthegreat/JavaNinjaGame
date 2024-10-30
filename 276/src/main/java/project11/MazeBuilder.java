    package project11;

    /**
     * MazeBuilder class used to populate the arary with GameObjects.
     * Uses the GameObjectFactory to create the GameObjects.
     * 
     * @author Felmer
     */
    public class MazeBuilder {
        public GameObject[][] maze;
        public GameObjectFactory factory;

        /**
         * Constructor for the MazeBuilder class.
         * 
         * @param width  initializes the width of the maze.
         * @param height initializes the height of the maze.
         */
        public MazeBuilder(GameObject[][] maze, GameObjectFactory factory) {
            this.maze = maze;
            this.factory = factory;
        }

        /**
         * Method to build the maze.
         */
        public void buildMaze() {

        }

        private void populateMaze() {

        }

        private void generateWalls(){

        }

        private void generateItems(){

        }

        private void generateHoles(){

        }
        private void generateBushes(){

        }
    }
