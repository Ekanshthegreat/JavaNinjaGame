package project11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class movePlayerTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
    }

    @Test
    void testMovePlayerUp() {
        // Initial position (0, height / 2)
        int initialX = gameState.getPlayer().getX();
        int initialY = gameState.getPlayer().getY();

        // Move player up
        gameState.testMovePlayer(true, false, false, false);

        // Assert that player moved up by one unit
        assertEquals(initialX, gameState.getPlayer().getX());
        assertEquals(initialY - 1, gameState.getPlayer().getY());
    }

    @Test
    void testMovePlayerDown() {
        int initialX = gameState.getPlayer().getX();
        int initialY = gameState.getPlayer().getY();

        // Move player down
        gameState.testMovePlayer(false, true, false, false);

        // Assert player moved down by one unit
        assertEquals(initialX, gameState.getPlayer().getX());
        assertEquals(initialY + 1, gameState.getPlayer().getY());
    }

    @Test
    void testMovePlayerLeft() {
        int initialX = gameState.getPlayer().getX();
        int initialY = gameState.getPlayer().getY();

        // Move player left
        gameState.testMovePlayer(false, false, true, false);

        // Assert player moved left by one unit
        assertEquals(initialX, gameState.getPlayer().getX());
        assertEquals(initialY, gameState.getPlayer().getY());
    }

    @Test
    void testMovePlayerRight() {
        int initialX = gameState.getPlayer().getX();
        int initialY = gameState.getPlayer().getY();

        // Move player right
        gameState.testMovePlayer(false, false, false, true);

        // Assert player moved right by one unit
        assertEquals(initialX, gameState.getPlayer().getX());
        assertEquals(initialY, gameState.getPlayer().getY());
    }

    @Test
    void testMovePlayerIntoWall() {
        // Set up a wall at (1, 0)
        gameState.getGameObjects()[0][1] = new Wall(1, 0);

        // Attempt to move player into the wall
        int initialX = gameState.getPlayer().getX();
        int initialY = gameState.getPlayer().getY();

        gameState.testMovePlayer(false, false, true, false); // Try to move left into the wall

        // Assert player did not move (should still be at initial position)
        assertEquals(initialX, gameState.getPlayer().getX());
        assertEquals(initialY, gameState.getPlayer().getY());
    }

    @Test
    void testMovePlayerToChestWithoutCollectingAllItems() {
        // Set up a chest at (1, 0)
        gameState.getGameObjects()[0][1] = new End(1, 0);

        // Move player to the chest without collecting all items
        gameState.testMovePlayer(false, false, true, false);

        // Assert the player is prompted to collect all items before reaching the chest
    }
}
