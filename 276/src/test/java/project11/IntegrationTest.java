package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Integration Test Class
public class IntegrationTest {

    private MazeBuilder mazeBuilder;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        // Avoid using Renderer directly as it depends on sprites
        GameObjectFactory factory = new GameObjectFactory();
        mazeBuilder = new MazeBuilder(factory);
        gameState = new GameState();
    }

    @Test
    void testMazeBuilderWithFactory() {
        GameObject[][] maze = new GameObject[Constants.getPlayRows()][Constants.getPlayColumns()];
        mazeBuilder.buildMaze(maze);

        // Verify that all cells are either Ground, Barrier, or Hole
        for (int i = 0; i < Constants.getPlayRows(); i++) {
            for (int j = 0; j < Constants.getPlayColumns(); j++) {
                GameObject obj = maze[i][j];
                assertNotNull(obj, "Maze cell (" + i + "," + j + ") should not be null.");
                int typeId = obj.getTypeId();
                assertTrue(typeId == 1 || typeId == 6 || typeId == 2,
                        "Maze cell (" + i + "," + j + ") should be Ground, Barrier, or Hole. Found: " + obj.getClass().getSimpleName());
            }
        }
    }

    @Test
    void testGameStateInteractionWithItems() {
        GameObject[][] gameObjects = gameState.getGameObjects();

        // Place a bonus item manually for testing
        gameObjects[1][1] = new BonusItem(1, 1);

        // Move player to collect the bonus item
        gameState.movePlayer(false, false, false, true); // Move right
        gameState.movePlayer(false, true, false, false); // Move down

        assertEquals(1, gameState.getBonusItem(), "Player should collect the bonus item.");
    }

    @Test
    void testEnemyInteractionInGameState() {
        Enemy enemy = new Samurai(5, 5);
        gameState.getGameObjects()[5][5] = enemy;

        Player player = new Player(3, 3);
        gameState.getGameObjects()[3][3] = player;

        // Move enemy toward player
        enemy.moveTowardsPlayer(player);

        // Check that the enemy moved closer to the player
        int distanceBefore = Math.abs(5 - 3) + Math.abs(5 - 3); // Manhattan distance
        int distanceAfter = Math.abs(enemy.getX() - player.getX()) + Math.abs(enemy.getY() - player.getY());
        assertTrue(distanceAfter < distanceBefore, "Enemy should move closer to the player.");
    }

    @Test
    void testRendererWithValidObjects() {
        // Simulate rendering logic with dummy game objects
        GameObject[][] dummyObjects = new GameObject[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dummyObjects[i][j] = new Ground(i, j);
            }
        }

        // Ensure rendering logic doesn't throw exceptions
        Renderer renderer = new Renderer();
        assertDoesNotThrow(() -> renderer.render(null, dummyObjects),
                "Rendering should not throw an exception.");
    }

    @Test
    void testRendererWithDummyGraphics() {
        Renderer renderer = new Renderer();

        // Simulate dummy Graphics object
        GameObject[][] dummyObjects = new GameObject[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dummyObjects[i][j] = new Ground(i, j);
            }
        }

        // Dummy rendering
        assertDoesNotThrow(() -> {
            renderer.render(null, dummyObjects);
        }, "Rendering should not throw an exception.");
    }
}
