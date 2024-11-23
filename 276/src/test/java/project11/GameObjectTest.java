package project11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectTest {

    @Test
    void testIsSolid() {
        GameObject ground = new Ground(0, 0);
        GameObject barrier = new Barrier(1, 1);

        assertFalse(ground.isSolid(), "Ground should not be solid.");
        assertTrue(barrier.isSolid(), "Barrier should be solid.");
    }

    @Test
    void testGettersAndSetters() {
        GameObject gameObject = new Ground(10, 20);

        assertEquals(10, gameObject.getX(), "Initial X should be correct.");
        assertEquals(20, gameObject.getY(), "Initial Y should be correct.");

        gameObject.setX(30);
        gameObject.setY(40);

        assertEquals(30, gameObject.getX(), "X coordinate should be updated correctly.");
        assertEquals(40, gameObject.getY(), "Y coordinate should be updated correctly.");
    }

    @Test
    void testGetTypeId() {
        GameObject ground = new Ground(0, 0);
        GameObject barrier = new Barrier(1, 1);

        assertEquals(1, ground.getTypeId(), "Ground should have type ID 1.");
        assertEquals(6, barrier.getTypeId(), "Barrier should have type ID 6.");
    }
}
