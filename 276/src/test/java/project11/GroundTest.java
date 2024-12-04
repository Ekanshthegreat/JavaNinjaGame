package project11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class GroundTest {

    @Test
    void testGroundProperties() {
        Ground Ground = new Ground(0,0);

        assertTrue(!Ground.isSolid(), "Ground should be solid");
        assertEquals(0, Ground.getX(), "X coordinate should be correct");
        assertEquals(0, Ground.getY(), "Y coordinate should be correct");
        assertEquals(1, Ground.getTypeId(), "Type ID should be correct");
    }
}
