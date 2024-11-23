package project11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class MandatoryItemTest {

    @Test
    void testMandatoryItemProperties() {
        MandatoryItem MandatoryItem = new MandatoryItem(0,0);

        assertTrue(!MandatoryItem.isSolid(), "MandatoryItem should be solid");
        assertEquals(0, MandatoryItem.getX(), "X coordinate should be correct");
        assertEquals(0, MandatoryItem.getY(), "Y coordinate should be correct");
        assertEquals(6, MandatoryItem.getTypeId(), "Type ID should be correct");
    }
}
