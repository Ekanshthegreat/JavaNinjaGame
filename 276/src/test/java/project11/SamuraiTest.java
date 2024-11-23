package project11;

public class SamuraiTest {
    @Test
    void testSamuraiProperties() {
        Samurai samurai = new Samurai();

        assertTrue(samurai.isSolid(), "Samurai should be solid");
        assertEquals(0, samurai.getX(), "X coordinate should be correct");
        assertEquals(0, samurai.getY(), "Y coordinate should be correct");
        assertEquals(7, samurai.getTypeId(), "Type ID should be correct");
    }
    
}
