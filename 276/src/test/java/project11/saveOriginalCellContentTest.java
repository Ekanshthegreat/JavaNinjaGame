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

    

    
}
