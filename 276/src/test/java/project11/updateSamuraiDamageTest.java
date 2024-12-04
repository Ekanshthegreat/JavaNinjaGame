package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    @Test
    void testUpdateSamuraiDamage() {
        // Create a Samurai enemy and add it to the game state
        Samurai samurai = new Samurai(1, 1);  // Position (1, 1)
        gameState.getEnemies().add(samurai);

        // Update the damage to 50
        gameState.testUpdateSamuraiDamage(50);

        // Assert that the damage of the Samurai was updated correctly
        assertEquals(50, samurai.getDamage(), "Samurai damage should be updated to 50.");
    }
}
