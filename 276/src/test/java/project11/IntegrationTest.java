package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Simplified Integration Test Class
public class IntegrationTest {

    private MazeBuilder mazeBuilder;

    @BeforeEach
    void setUp() {
        // Initialize only essential components
        GameObjectFactory factory = new GameObjectFactory();
        mazeBuilder = new MazeBuilder(factory);
    }

    @Test
    void testMazeBuilderWithFactory() {
        GameObject[][] maze = new GameObject[Constants.getPlayRows()][Constants.getPlayColumns()];
        mazeBuilder.buildMaze(maze);

        // Verify that all cells are initialized
        for (int i = 0; i < Constants.getPlayRows(); i++) {
            for (int j = 0; j < Constants.getPlayColumns(); j++) {
                assertNotNull(maze[i][j], "Maze cell (" + i + "," + j + ") should not be null.");
            }
        }
    }

    @Test
    void testEnemyInteraction() {
        // Test for basic enemy interactions (e.g., movement logic)
        Enemy enemy = new Samurai(5, 5);
        Player player = new Player(3, 3);

        // Simulate movement toward player
        enemy.moveTowardsPlayer(player);

        // Ensure the enemy has moved closer
        int distanceAfter = Math.abs(enemy.getX() - player.getX()) + Math.abs(enemy.getY() - player.getY());
        assertTrue(distanceAfter < 4, "Enemy should move closer to the player.");
    }
}
