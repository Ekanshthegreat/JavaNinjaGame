package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameObjectFactoryTest {

    @Test
    void testCreateObject() {
        GameObjectFactory factory = new GameObjectFactory();

        GameObject ground = factory.createObject("ground", 10, 20);
        assertTrue(ground instanceof Ground, "Object should be of type Ground");
        assertEquals(10, ground.getX(), "X coordinate should be correct");
        assertEquals(20, ground.getY(), "Y coordinate should be correct");

        GameObject samurai = factory.createObject("samurai", 50, 60);
        assertTrue(samurai instanceof Samurai, "Object should be of type Samurai");
        assertEquals(50, samurai.getX(), "X coordinate should be correct");
        assertEquals(60, samurai.getY(), "Y coordinate should be correct");

        assertThrows(IllegalArgumentException.class, () -> factory.createObject("unknown", 0, 0), 
                     "Creating an unknown type should throw an exception");
    }
}
