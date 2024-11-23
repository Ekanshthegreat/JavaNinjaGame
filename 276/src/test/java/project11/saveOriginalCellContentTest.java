package project11;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

class SaveOriginalCellContentTest {

    private GameState gameState;
    private GameObjectFactory gameObjectFactory;

    @BeforeEach
    void setUp() {
        gameState = new GameState();
        gameObjectFactory = new GameObjectFactory();
    }

    @Test
    void testSaveOriginalCellContent_SavesNonEnemyObject() {
        // Create a new object (non-enemy) at position (2, 2)
        int x = 2;
        int y = 2;
        GameObject originalObject = gameObjectFactory.createObject(1, x, y);

        // Call saveOriginalCellContent
        gameState.testSaveOriginalCellContent(x, y, originalObject);

        // Access the originalObjects map and verify the object is saved
        Map<String, GameObject> originalObjects = gameState.getOriginalObjects();
        assertTrue(originalObjects.containsKey(x + "," + y));
        assertEquals(originalObject, originalObjects.get(x + "," + y));
    }

    @Test
    void testSaveOriginalCellContent_DoesNotSaveEnemyObject() {
        // Create an enemy at position (3, 3)
        int x = 3;
        int y = 3;
        Enemy enemy = new Samurai(x, y);

        // Call saveOriginalCellContent
        gameState.testSaveOriginalCellContent(x, y, enemy);

        // Access the originalObjects map and verify the object is not saved
        Map<String, GameObject> originalObjects = gameState.getOriginalObjects();
        assertFalse(originalObjects.containsKey(x + "," + y));
    }

    @Test
    void testSaveOriginalCellContent_OnlySavesOnce() {
        // Create a non-enemy object at position (4, 4)
        int x = 4;
        int y = 4;
        GameObject originalObject = gameObjectFactory.createObject(1, x, y);

        // Call saveOriginalCellContent
        gameState.testSaveOriginalCellContent(x, y, originalObject);

        // Call saveOriginalCellContent again with the same position
        gameState.testSaveOriginalCellContent(x, y, gameObjectFactory.createObject(1, x, y));

        // Verify that the original object wasn't overwritten
        Map<String, GameObject> originalObjects = gameState.getOriginalObjects();
        assertEquals(originalObject, originalObjects.get(x + "," + y));
    }
}
