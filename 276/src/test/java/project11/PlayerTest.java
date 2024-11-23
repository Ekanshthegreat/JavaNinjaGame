package project11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void testPlayerProperties() {
        Player Player = new Player(0,0);

        assertTrue(Player.isSolid(), "Player should be solid");
        assertEquals(0, Player.getX(), "X coordinate should be correct");
        assertEquals(0, Player.getY(), "Y coordinate should be correct");
        assertEquals(6, Player.getTypeId(), "Type ID should be correct");
    }
}
