package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    @Test
    void testWallProperties() {
        Wall Wall = new Wall(0,0);

        assertTrue(Wall.isSolid(), "Wall should be solid");
        assertEquals(0, Wall.getX(), "X coordinate should be correct");
        assertEquals(0, Wall.getY(), "Y coordinate should be correct");
        assertEquals(6, Wall.getTypeId(), "Type ID should be correct");
    }
}
