package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SetDifficultyTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    @Test
    void testSetDifficultyEasy() {
        // Set difficulty to easy
        gameState.setDifficulty(0);

        // Verify all Samurai enemies have the expected damage value for easy difficulty
        List<Enemy> enemies = gameState.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Samurai) {
                assertEquals(25, ((Samurai) enemy).getDamage(), "Damage for Samurai should be 25 on easy difficulty");
            }
        }
    }

    @Test
    void testSetDifficultyMedium() {
        // Set difficulty to medium
        gameState.setDifficulty(1);

        // Verify all Samurai enemies have the expected damage value for medium difficulty
        List<Enemy> enemies = gameState.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Samurai) {
                assertEquals(35, ((Samurai) enemy).getDamage(), "Damage for Samurai should be 35 on medium difficulty");
            }
        }
    }

    @Test
    void testSetDifficultyHard() {
        // Set difficulty to hard
        gameState.setDifficulty(2);

        // Verify all Samurai enemies have the expected damage value for hard difficulty
        List<Enemy> enemies = gameState.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Samurai) {
                assertEquals(35, ((Samurai) enemy).getDamage(), "Damage for Samurai should be 35 on hard difficulty");
            }
        }

        // Verify additional Samurai enemies are spawned for hard difficulty
        long samuraiCount = enemies.stream().filter(enemy -> enemy instanceof Samurai).count();
        assertTrue(samuraiCount >= 5, "Hard difficulty should spawn at least 3 additional Samurai enemies");
    }
}
