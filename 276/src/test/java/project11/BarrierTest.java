package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {

    @Test
    void testBarrierProperties() {
        Barrier barrier = new Barrier(15, 25, true, 6);

        assertTrue(barrier.isSolid(), "Barrier should be solid");
        assertEquals(15, barrier.getX(), "X coordinate should be correct");
        assertEquals(25, barrier.getY(), "Y coordinate should be correct");
        assertEquals(6, barrier.getTypeId(), "Type ID should be correct");
    }
}
