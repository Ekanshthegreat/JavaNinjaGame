package project11;

import java.util.ArrayList;
import java.util.Random;

public class EnemyGenerator {
    private ArrayList<Enemy> enemies;
    private Player player;
    private EnemyAI enemyAI;
    private Random random;

    public EnemyGenerator(Player player) {
        this.enemies = new ArrayList<>();
        this.player = player;
        this.enemyAI = new EnemyAI(player);
        this.random = new Random();
    }

    public Enemy createEnemy() {
        int x = random.nextInt(500); 
        int y = random.nextInt(500);
        Enemy enemy = new Samurai(x, y, 10, 4); // Adjust as necessary
        enemies.add(enemy);
        return enemy;
    }

    public void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemyAI.moveTowardsPlayer(enemy); // Move each enemy towards the player
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}
