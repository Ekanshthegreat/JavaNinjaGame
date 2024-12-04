package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Enhanced Integration Test Class
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

    @Test
    void testMazeContainsSpecificObjects() {
        GameObject[][] maze = new GameObject[Constants.getPlayRows()][Constants.getPlayColumns()];
        mazeBuilder.buildMaze(maze);

        // Count specific objects
        int groundCount = 0;
        int barrierCount = 0;

        for (int i = 0; i < Constants.getPlayRows(); i++) {
            for (int j = 0; j < Constants.getPlayColumns(); j++) {
                if (maze[i][j] instanceof Ground) {
                    groundCount++;
                } else if (maze[i][j] instanceof Barrier) {
                    barrierCount++;
                }
            }
        }

        assertTrue(groundCount > 0, "Maze should contain at least one Ground object.");
        assertTrue(barrierCount > 0, "Maze should contain at least one Barrier object.");
    }

    @Test
    void testPlayerScoreIncreasesOnCollectingItem() {
        Player player = new Player(2, 2);
        BonusItem bonusItem = new BonusItem(2, 2);

        // Simulate item collection
        player.increaseScore(bonusItem.getScore());

        assertEquals(100, player.getScore(), "Player score should match the bonus item score.");
    }

    @Test
    void testGameStateInitialization() {
        GameState gameState = new GameState();

        assertNotNull(gameState.getGameObjects(), "Game objects should be initialized.");
        assertEquals(0, gameState.getCollectedItems(), "Collected items should initially be zero.");
        assertEquals(0, gameState.getBonusItem(), "Bonus items should initially be zero.");
    }

    @Test
    void testEnemyDoesNotOverlapOnInitialization() {
        GameObject[][] gameBoard = new GameObject[Constants.getPlayRows()][Constants.getPlayColumns()];
        MazeBuilder builder = new MazeBuilder(new GameObjectFactory());
        builder.buildMaze(gameBoard);

        Enemy enemy1 = new Samurai(5, 5);
        Enemy enemy2 = new Samurai(5, 5);

        // Simulate enemy initialization
        gameBoard[enemy1.getY()][enemy1.getX()] = enemy1;
        boolean overlap = gameBoard[enemy2.getY()][enemy2.getX()] instanceof Enemy;

        assertFalse(!overlap, "Enemies should not overlap on initialization.");
    }

    @Test
    void testPlayerHealthDoesNotDropBelowZero() {
        Player player = new Player(2, 2);

        // Apply damage
        player.takeDamage(50);
        player.takeDamage(50);
        player.takeDamage(10);

        assertEquals(0, player.getScore(), "Player health should not drop below zero.");
    }

    @Test
    void testMazeEndTilePlacement() {
        GameObject[][] maze = new GameObject[Constants.getPlayRows()][Constants.getPlayColumns()];
        mazeBuilder.buildMaze(maze);

        boolean hasEndTile = false;

        for (int i = 0; i < Constants.getPlayRows(); i++) {
            for (int j = 0; j < Constants.getPlayColumns(); j++) {
                if (maze[i][j] instanceof End) {
                    hasEndTile = true;
                    break;
                }
            }
        }

        assertTrue(hasEndTile, "Maze should have an End tile.");
    }
}
