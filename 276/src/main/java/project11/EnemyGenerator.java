package project11;

import java.util.ArrayList;
import java.util.Random;

/**
 * Enemy Generator class
 */
public class EnemyGenerator {
    private ArrayList<Enemy> enemies;
    private Player player;
    private Random random;

    /**
     * Constructor
     * @param player Player object
     */
    public EnemyGenerator(Player player) {
        this.enemies = new ArrayList<>();
        this.player = player;
        this.random = new Random();
    }

    /**
     * Create an enemy object randomly
     * @return Enemy object
     */
    public Enemy createEnemy() {
        int x = random.nextInt(500); 
        int y = random.nextInt(500);
        Enemy enemy = new Samurai(x, y, 10, 4); // Adjust as necessary
        enemies.add(enemy);
        return enemy;
    }

    /**
     * Function to loop through all enemies and move them simultaneously
     */
    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.moveTowardsPlayer(player); // Move each enemy towards the player
        }
    }

    /**
     * Getter
     * @return Current list of enemies
     */
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
