package project11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class HoleTest {

    @Test
    void testHoleProperties() {
        Hole Hole = new Hole(0,0);

        assertTrue(Hole.isSolid(), "Hole should be solid");
        assertEquals(0, Hole.getX(), "X coordinate should be correct");
        assertEquals(0, Hole.getY(), "Y coordinate should be correct");
        assertEquals(2, Hole.getTypeId(), "Type ID should be correct");
    }
}
