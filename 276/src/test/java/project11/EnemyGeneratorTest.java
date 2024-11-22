package project11;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnemyGeneratorTest {

    @Test
    void testCreateEnemy() {
        Player player = new Player(10, 10, 5);
        EnemyGenerator generator = new EnemyGenerator(player);

        Enemy enemy = generator.createEnemy();

        assertNotNull(enemy, "Generated enemy should not be null");
        assertTrue(enemy instanceof Samurai, "Generated enemy should be of type Samurai");
        assertTrue(enemy.getX() >= 0 && enemy.getX() < 500, "Enemy X position should be within bounds");
        assertTrue(enemy.getY() >= 0 && enemy.getY() < 500, "Enemy Y position should be within bounds");
    }

    @Test
    void testUpdateEnemies() {
        Player player = new Player(10, 10, 5);
        EnemyGenerator generator = new EnemyGenerator(player);

        Enemy enemy1 = generator.createEnemy();
        Enemy enemy2 = generator.createEnemy();
        enemy1.setX(5);
        enemy1.setY(5);
        enemy2.setX(15);
        enemy2.setY(15);

        generator.updateEnemies();

        assertTrue(enemy1.getX() > 5 || enemy1.getY() > 5, "Enemy 1 should move towards the player");
        assertTrue(enemy2.getX() < 15 || enemy2.getY() < 15, "Enemy 2 should move towards the player");
    }
}
