package project11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameObjectFactoryTest {

    @Test
    void testCreateObject() {
        GameObjectFactory factory = new GameObjectFactory();

        GameObject ground = factory.createObject(1, 0, 0);
        assertNotNull(ground, "Ground object should not be null.");
        assertTrue(ground instanceof Ground, "Ground object should be of type Ground.");
        assertEquals(1, ground.getTypeId(), "Ground should have type ID 1.");

        GameObject barrier = factory.createObject(6, 5, 5);
        assertNotNull(barrier, "Barrier object should not be null.");
        assertTrue(barrier instanceof Barrier, "Barrier object should be of type Barrier.");
        assertEquals(6, barrier.getTypeId(), "Barrier should have type ID 6.");

        assertThrows(IllegalArgumentException.class, () -> factory.createObject(99, 0, 0),
                "Creating an unknown object type should throw an exception.");
    }
}
