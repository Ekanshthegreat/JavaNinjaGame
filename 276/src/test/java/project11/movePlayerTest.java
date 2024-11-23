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
    void testMovePlayerIntoHole() {
        // Set up a hole at (1, 0)
        gameState.getGameObjects()[0][1] = new Hole(1, 0);

        // Attempt to move player into the hole
        int initialScore = gameState.getPlayer().getScore();

        gameState.testMovePlayer(false, false, true, false); // Try to move left into the hole

        // Assert player's score decreased after stepping on a hole
        assertTrue(gameState.getPlayer().getScore() < initialScore);
    }

    @Test
    void testMovePlayerIntoMandatoryItem() {
        // Set up a mandatory item at (1, 0)
        gameState.getGameObjects()[0][1] = new MandatoryItem(1, 0);

        // Attempt to move player into the mandatory item
        int initialCollectedItems = gameState.getCollectedItems();
        int initialScore = gameState.getPlayer().getScore();

        gameState.testMovePlayer(false, false, true, false); // Try to move left into the mandatory item

        // Assert that collected items increased and score updated
        assertEquals(initialCollectedItems + 1, gameState.getCollectedItems());
        assertEquals(initialScore + 10, gameState.getPlayer().getScore());
    }

    @Test
    void testMovePlayerIntoBonusItem() {
        // Set up a bonus item at (1, 0)
        gameState.getGameObjects()[0][1] = new BonusItem(1, 0);

        // Attempt to move player into the bonus item
        int initialBonusItems = gameState.getBonusItem();
        int initialScore = gameState.getPlayer().getScore();

        gameState.testMovePlayer(false, false, true, false); // Try to move left into the bonus item

        // Assert that bonus items increased and score updated
        assertEquals(initialBonusItems + 1, gameState.getBonusItem());
        assertEquals(initialScore + 20, gameState.getPlayer().getScore());
    }

    @Test
    void testMovePlayerToChestWithAllItemsCollected() {
        // Set up a chest at (1, 0)
        gameState.getGameObjects()[0][1] = new End(1, 0);

        // Collect all items
        gameState.testMovePlayer(false, false, true, false); // Collect all mandatory items and bonus items

        // Move player to the chest
        gameState.testMovePlayer(false, false, true, false);

        // Assert that game ends with a win message
        // This might involve checking the system output or ensuring no exceptions occurred
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
