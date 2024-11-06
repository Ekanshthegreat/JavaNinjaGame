package project11;

/**
 * Enemy AI class for Enemy
 */
public class EnemyAI {
    private Player player;

    public EnemyAI(Player player) {
        this.player = player;
    }

    /**
     * Calculate the enemy movement
     * @param enemy Enemy object
     */
    public void moveTowardsPlayer(Enemy enemy) {
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();
        int playerX = player.getX();
        int playerY = player.getY();

        // Move enemy towards player
        if (enemyX < playerX) {
            enemy.setX(enemyX + 1); // Move right
        } else if (enemyX > playerX) {
            enemy.setX(enemyX - 1); // Move left
        }

        if (enemyY < playerY) {
            enemy.setY(enemyY + 1); // Move down
        } else if (enemyY > playerY) {
            enemy.setY(enemyY - 1); // Move up
        }
    }
}
