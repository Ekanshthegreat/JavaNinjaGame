package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void testIsSolid() {
        GameObject obj = new Ground(10, 10, true, 1);
        assertTrue(obj.isSolid(), "The object should be solid");
    }

    @Test
    void testGettersAndSetters() {
        GameObject obj = new Ground(10, 20, false, 1);

        assertEquals(10, obj.getX(), "X coordinate should be correct");
        assertEquals(20, obj.getY(), "Y coordinate should be correct");
        assertEquals(1, obj.getTypeId(), "Type ID should be correct");

        obj.setX(30);
        obj.setY(40);

        assertEquals(30, obj.getX(), "X coordinate should update correctly");
        assertEquals(40, obj.getY(), "Y coordinate should update correctly");
    }
}
