package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegrationTests {

    private GameObjectFactory factory;
    private EnemyGenerator enemyGenerator;
    private MazeBuilder mazeBuilder;
    private Player player;
    private GameObject[][] maze;

    @BeforeEach
    void setUp() {
        factory = new GameObjectFactory();
        player = new Player(5, 5, 100); // Start player at center with 100 health
        enemyGenerator = new EnemyGenerator(player);
        mazeBuilder = new MazeBuilder(factory);
        maze = new GameObject[GamePanel.getPlayRows()][GamePanel.getPlayColumns()];
    }

//     @Test
// void testMazeGeneration() {
//     mazeBuilder.buildMaze(maze);

//     // Check if the maze is fully populated
//     for (int row = 0; row < maze.length; row++) {
//         for (int col = 0; col < maze[row].length; col++) {
//             GameObject obj = maze[row][col];
//             assertNotNull(obj, "Maze cell (" + row + "," + col + ") should not be null.");
//             assertTrue(
//                     obj instanceof Ground || obj instanceof Barrier,
//                     "Maze cell (" + row + "," + col + ") should be Ground or Barrier. Found: " + obj
//             );
//         }
//     }
// }


    // @Test
    // void testEnemyPlacement() {
    //     mazeBuilder.buildMaze(maze);
    //     Enemy enemy = enemyGenerator.createEnemy();

    //     // Verify enemy is added to the maze
    //     int enemyX = enemy.getX();
    //     int enemyY = enemy.getY();
    //     assertNotNull(maze[enemyY][enemyX], "Enemy should be placed within the maze bounds.");
    //     assertTrue(enemy instanceof Samurai, "Enemy should be of type Samurai.");
    // }

    @Test
    void testGameObjectFactoryIntegration() {
        // Use factory to create objects and validate their properties
        GameObject ground = factory.createObject("ground", 1, 1);
        GameObject barrier = factory.createObject("barrier", 2, 2);

        assertNotNull(ground, "Factory should create a ground object.");
        assertEquals(1, ground.getTypeId(), "Ground object should have type ID 1.");

        assertNotNull(barrier, "Factory should create a barrier object.");
        assertTrue(barrier.isSolid(), "Barrier object should be solid.");
        assertEquals(6, barrier.getTypeId(), "Barrier object should have type ID 6.");
    }

    @Test
    void testEnemyMovementTowardsPlayer() {
        Enemy enemy = enemyGenerator.createEnemy();
        enemy.setX(2);
        enemy.setY(2);

        int initialX = enemy.getX();
        int initialY = enemy.getY();

        enemyGenerator.updateEnemies();

        // Enemy should move closer to the player
        assertTrue(enemy.getX() > initialX || enemy.getY() > initialY, "Enemy should move closer to the player.");
    }

    // @Test
    // void testIntegrationWithMazeAndPlayer() {
    //     mazeBuilder.buildMaze(maze);
    //     Enemy enemy = enemyGenerator.createEnemy();

    //     // Place the player in the maze
    //     maze[player.getY()][player.getX()] = player;

    //     // Ensure the player starts at the correct position
    //     assertEquals(player, maze[5][5], "Player should be placed at the center of the maze.");

    //     // Place the enemy and move it towards the player
    //     maze[enemy.getY()][enemy.getX()] = enemy;
    //     enemyGenerator.updateEnemies();

    //     // Ensure the enemy moved closer to the player
    //     int playerX = player.getX();
    //     int playerY = player.getY();
    //     int enemyX = enemy.getX();
    //     int enemyY = enemy.getY();

    //     assertTrue(Math.abs(enemyX - playerX) <= 1 && Math.abs(enemyY - playerY) <= 1,
    //             "Enemy should be adjacent to the player after movement.");
    // }
}
