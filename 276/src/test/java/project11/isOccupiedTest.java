package project11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class isOccupiedTest {

    private GameState gameState;

    @BeforeEach
    void setUp() {
        gameState = new GameState();  // Initialize the GameState before each test
    }

    @Test
    void testIsOccupiedWhenEmptyCell() {
        // Test case where the cell is empty (not occupied)
        int x = 5;
        int y = 5;
        // Ensure the cell at (5,5) is not occupied (assuming it's initialized as empty)
        assertTrue(gameState.testIsOccupied(x, y), "Cell should not be occupied.");
    }

    @Test
    void testIsOccupiedWhenCellHasSolidObject() {
        // Test case where the cell has a solid object
        int x = 2;
        int y = 2;

        // Assuming the game state already has a solid object in this cell
        // For this example, let's assume we are using a `Ground` object as a solid object.
        GameObject ground = new Ground(x, y);
        gameState.testSaveOriginalCellContent(x, y, ground);  // Save this object in the cell

        // Now check if the cell is occupied
        assertFalse(gameState.testIsOccupied(x, y), "Cell should be occupied.");
    }

    @Test
    void testIsOccupiedWhenCellHasNonSolidObject() {
        // Test case where the cell has a non-solid object (e.g., an item)
        int x = 3;
        int y = 3;

        // Assume a non-solid object (like a `BonusItem`) is placed at (3,3)
        GameObject bonusItem = new BonusItem(x, y);
        gameState.testSaveOriginalCellContent(x, y, bonusItem);  // Save this object in the cell

        // Now check if the cell is occupied (it should not be because it's non-solid)
        assertTrue(gameState.testIsOccupied(x, y), "Cell should not be occupied.");
    }

    @Test
    void testIsOccupiedWhenCellHasEnemy() {
        // Test case where the cell has an enemy
        int x = 4;
        int y = 4;

        // Place an enemy (e.g., Samurai) at (4,4)
        Samurai samurai = new Samurai(x, y);
        gameState.testSaveOriginalCellContent(x, y, samurai);  // Save enemy in the cell

        // Now check if the cell is occupied
        assertFalse(gameState.testIsOccupied(x, y), "Cell should be occupied by the enemy.");
    }
}
