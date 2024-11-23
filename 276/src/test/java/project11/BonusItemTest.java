package project11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BonusItemTest {

    @Test
    void testBonusItemProperties() {
        BonusItem BonusItem = new BonusItem(0,0);

        assertTrue(!BonusItem.isSolid(), "BonusItem should be solid");
        assertEquals(0, BonusItem.getX(), "X coordinate should be correct");
        assertEquals(0, BonusItem.getY(), "Y coordinate should be correct");
        assertEquals(6, BonusItem.getTypeId(), "Type ID should be correct");
    }
}
