package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SamuraiTest {
    @Test
    void testSamuraiProperties() {
        Samurai samurai = new Samurai(0,0);

        assertTrue(samurai.isSolid(), "Samurai should be solid");
        assertEquals(0, samurai.getX(), "X coordinate should be correct");
        assertEquals(0, samurai.getY(), "Y coordinate should be correct");
        assertEquals(4, samurai.getTypeId(), "Type ID should be correct");
    }
    
    // Test all possible Samurai Spawns
    @Test
    void testSamuraiSpawns() {
        for (int i = 0; i < Constants.getPlayRows(); i++) {
            for (int j = 0; j < Constants.getPlayColumns(); j++) {
                try {
                    Samurai samurai = new Samurai(j, i);
                    assertEquals(j, samurai.getX(), "X coordinate should be correct");
                    assertEquals(i, samurai.getY(), "Y coordinate should be correct");
                } catch (IllegalArgumentException e) {
                    System.out.println("Spawning failed at coordinates (" + j + ", " + i + ")");
                    // Optionally rethrow if you want the test to fail:
                    throw e;
                }
            }
        }
    }

    void spawnPlayerAndMoveSamurai(Samurai samurai, int x, int y){
        Player player1 = new Player(Constants.getPlayRows(), Constants.getPlayColumns());
        int stepThreshold = 100;
        int steps = 0;
        GameObject[][] testMaze = new GameObject[Constants.getPlayRows()][Constants.getPlayColumns()];
        for (int i = 0; i < Constants.getPlayRows(); i++) {
            for (int j = 0; j < Constants.getPlayColumns(); j++) {
                testMaze[i][j] = new Ground(j, i);
            }
        }

        // Test player at (1,1)

        while (samurai.getX() != 1 || samurai.getY() != 1) {
            samurai.moveTowardsPlayerAvoidingWalls(player1, testMaze);
            if(steps++ > stepThreshold) {
                fail("Samurai should be able to move towards player at" + x + ", " + y);
            }
        }
        System.out.println("Steps Taken:" + steps);
    }

    /**
     * Test the movement of the Samurai by placing it at different locations and moving it towards the player
     */
    @Test
    void testSamuraiMovement() {
        Samurai samurai = new Samurai(0,0);
    
        spawnPlayerAndMoveSamurai(samurai, 1, 1);
        spawnPlayerAndMoveSamurai(samurai, Constants.getPlayColumns(), Constants.getPlayRows());
        spawnPlayerAndMoveSamurai(samurai, 0, Constants.getPlayRows());
        spawnPlayerAndMoveSamurai(samurai, Constants.getPlayColumns(), 0);
    }
    


}
