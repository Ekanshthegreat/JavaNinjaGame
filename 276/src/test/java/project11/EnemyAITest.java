package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnemyAITest {

    @Test
    void testMoveTowardsPlayer() {
        Player player = new Player(10, 10, 5);
        Enemy enemy = new Samurai(5, 5, 10, 4);
        EnemyAI enemyAI = new EnemyAI(player);

        enemyAI.moveTowardsPlayer(enemy);

        assertEquals(6, enemy.getX(), "Enemy should move right towards the player");
        assertEquals(6, enemy.getY(), "Enemy should move down towards the player");
    }
}
