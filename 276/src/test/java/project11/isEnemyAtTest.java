package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class isEnemyAtTest {
    private GameState gameState;
    private Player player;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
        player = new Player(0, 0);  // You can modify this if needed
        gameState.getGameObjects()[player.getY()][player.getX()] = player;
    }

    @Test
    void testIsEnemyAt_EnemyPresent() {
        // Arrange: Place an enemy on the board
        Samurai samurai = new Samurai(1, 1); // Assuming (1, 1) is empty
        gameState.getGameObjects()[samurai.getY()][samurai.getX()] = samurai;

        // Act & Assert: Check if isEnemyAt returns true for the position with the enemy
        assertTrue(gameState.testIsEnemyAt(1, 1), "Enemy should be at (1, 1)");
    }

    @Test
    void testIsEnemyAt_EnemyAbsent() {
        // Act & Assert: Check if isEnemyAt returns false for an empty position
        assertFalse(gameState.testIsEnemyAt(2, 2), "No enemy should be at (2, 2)");
    }

    @Test
    void testIsEnemyAt_PlayerPosition() {
        // Act & Assert: Check if isEnemyAt returns false for player position
        assertFalse(gameState.testIsEnemyAt(player.getX(), player.getY()), "Player should not be considered an enemy");
    }

    @Test
    void testIsEnemyAt_MultipleEnemies() {
        // Arrange: Place multiple enemies on the board
        Samurai samurai1 = new Samurai(1, 1);
        Samurai samurai2 = new Samurai(2, 2);
        gameState.getGameObjects()[samurai1.getY()][samurai1.getX()] = samurai1;
        gameState.getGameObjects()[samurai2.getY()][samurai2.getX()] = samurai2;

        // Act & Assert: Check if isEnemyAt returns true for both positions
        assertTrue(gameState.testIsEnemyAt(1, 1), "Enemy should be at (1, 1)");
        assertTrue(gameState.testIsEnemyAt(2, 2), "Enemy should be at (2, 2)");
    }

    @Test
    void testIsEnemyAt_EnemyMoved() {
        // Arrange: Place an enemy and move it
        Samurai samurai = new Samurai(1, 1);
        gameState.getGameObjects()[samurai.getY()][samurai.getX()] = samurai;
        gameState.getGameObjects()[1][1] = null; // Empty the previous position
        samurai.setX(2);
        samurai.setY(2);
        gameState.getGameObjects()[samurai.getY()][samurai.getX()] = samurai;

        // Act & Assert: Check if isEnemyAt returns true for the new position
        assertTrue(gameState.testIsEnemyAt(2, 2), "Enemy should have moved to (2, 2)");
        assertFalse(gameState.testIsEnemyAt(1, 1), "Enemy should no longer be at (1, 1)");
    }
}
