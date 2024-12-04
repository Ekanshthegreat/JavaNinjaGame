package project11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SpawnAdditionalSamuraiTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        // Create a new instance of GameState before each test
        gameState = new GameState();
    }

    @Test
    void testSpawnSamuraiIncreasesEnemyCount() {
        int initialEnemyCount = gameState.getEnemies().size();

        // Spawn additional Samurai (e.g., 3 Samurai)
        gameState.testSpawnAdditionalSamurai(3);

        int newEnemyCount = gameState.getEnemies().size();
        assertEquals(initialEnemyCount + 3, newEnemyCount, "Number of enemies should increase by 3");
    }

    @Test
    void testSamuraiAreSpawnedInValidPositions() {
        // Spawn additional Samurai
        gameState.testSpawnAdditionalSamurai(3);

        List<Enemy> enemies = gameState.getEnemies();

        // Ensure all Samurai are spawned in valid (non-occupied) positions
        for (Enemy enemy : enemies) {
            int x = enemy.getX();
            int y = enemy.getY();

            // Check that the position is not occupied by another enemy or a solid object
            assertTrue(gameState.getGameObjects()[y][x] instanceof Samurai, "Enemy should be a Samurai");
            assertTrue(gameState.testIsOccupied(x, y), "Position should not be occupied by a solid object");
            assertTrue(gameState.testIsEnemyAt(x, y), "Position should not have another enemy");
        }
    }

    @Test
    void testSamuraiDamageIsCorrect() {
        // Get the initial damage value for Samurai
        int samuraiDamage = Constants.getSamuraiDamage();

        // Spawn additional Samurai
        gameState.testSpawnAdditionalSamurai(3);

        List<Enemy> enemies = gameState.getEnemies();

        // Ensure all spawned Samurai have the correct damage
        for (Enemy enemy : enemies) {
            if (enemy instanceof Samurai) {
                assertEquals(samuraiDamage, Constants.getSamuraiDamage(), "Samurai damage should be correctly set");
            }
        }
    }
}
