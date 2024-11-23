package project11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarrierTest {

    @Test
    void testBarrierProperties() {
        Barrier barrier = new Barrier(15, 25);

        assertTrue(barrier.isSolid(), "Barrier should be solid.");
        assertEquals(15, barrier.getX(), "Barrier X coordinate should be correct.");
        assertEquals(25, barrier.getY(), "Barrier Y coordinate should be correct.");
        assertEquals(6, barrier.getTypeId(), "Barrier should have type ID 6.");
    }
}
