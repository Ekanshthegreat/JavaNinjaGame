package project11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class EndTest {

    @Test
    void testEndProperties() {
        End End = new End(0,0);

        assertTrue(!End.isSolid(), "End should be solid");
        assertEquals(0, End.getX(), "X coordinate should be correct");
        assertEquals(0, End.getY(), "Y coordinate should be correct");
        assertEquals(9, End.getTypeId(), "Type ID should be correct");
    }
}
