package project11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class isAdjacentToPlayerTest {

    private GameState gameState;
    private Player player;

    @BeforeEach
    void setUp() {
        // Initialize the GameState and Player
        gameState = new GameState();

        // Initialize the player in a default position (e.g., (5, 5))
        player = new Player(5, 5); // Assuming setPosition is available on Player class
        player = gameState.getPlayer();
    }

    @Test
    void testEnemyIsAdjacentUp() {
        // Create an enemy directly above the player
        Enemy enemy = new Samurai(player.getX(), player.getY() - 1);  // Position above player
        assertTrue(gameState.testIsAdjacentToPlayer(enemy), "Enemy should be adjacent above the player.");
    }

    @Test
    void testEnemyIsAdjacentDown() {
        // Create an enemy directly below the player
        Enemy enemy = new Samurai(player.getX(), player.getY() + 1);  // Position below player
        assertTrue(gameState.testIsAdjacentToPlayer(enemy), "Enemy should be adjacent below the player.");
    }

    @Test
    void testEnemyIsAdjacentLeft() {
        // Create an enemy directly to the left of the player
        Enemy enemy = new Samurai(player.getX() - 1, player.getY());  // Position left of player
        assertTrue(gameState.testIsAdjacentToPlayer(enemy), "Enemy should be adjacent to the left of the player.");
    }

    @Test
    void testEnemyIsAdjacentRight() {
        // Create an enemy directly to the right of the player
        Enemy enemy = new Samurai(player.getX() + 1, player.getY());  // Position right of player
        assertTrue(gameState.testIsAdjacentToPlayer(enemy), "Enemy should be adjacent to the right of the player.");
    }

    @Test
    void testEnemyIsNotAdjacent() {
        // Create an enemy far from the player (not adjacent)
        Enemy enemy = new Samurai(player.getX() + 2, player.getY());  // Position further than adjacent
        assertFalse(gameState.testIsAdjacentToPlayer(enemy), "Enemy should not be adjacent to the player.");
    }

    @Test
    void testEnemyAtCornerIsAdjacent() {
        // Place player at corner and check if enemy is adjacent
        player = new Player(1, 1); // Player at corner (1, 1)
        Enemy enemy = new Samurai(0, 1); // Enemy at adjacent position
        assertFalse(gameState.testIsAdjacentToPlayer(enemy), "Enemy at corner should be adjacent to the player.");
    }

    @Test
    void testEnemyAtEdgeIsAdjacent() {
        // Place player at edge and check if enemy is adjacent
        player = new Player(5, 5); // Player at edge (5, 5)
        Enemy enemy = new Samurai(6, 5); // Enemy at adjacent position
        assertFalse(gameState.testIsAdjacentToPlayer(enemy), "Enemy at edge should be adjacent to the player.");
    }
}
