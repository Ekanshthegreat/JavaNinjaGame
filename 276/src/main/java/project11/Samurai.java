package project11;

import java.util.Random;

/**
 * Samurai class to extend Enemy
 */
public class Samurai extends Enemy {

    private static final int DAMAGE = 20;
    private Random random = new Random();

    /**
     * Make a Samurai which extends Enemy
     * @param x X Coordinate of samurai
     * @param y Y Coordinate of samurai
     * @param damage How much damage the samurai does
     * @param typeId Holds samurai id
     */
    public Samurai(int x, int y, int damage, int typeId) {
        super(x, y, damage, typeId);
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage += difficulty;
    }

    /**
     * Attack the player, deal damage, and remove the Samurai from the game
     */
    public void attackPlayer(Player player, GameState gameState) {
        player.takeDamage(DAMAGE);
        System.out.println("Samurai attacked player for " + DAMAGE + " damage!");

        // Remove Samurai and replace with Ground
        gameState.removeEnemy(this);
        gameState.setGround(this.getX(), this.getY());
    }

    /**
     * Attempt to move towards the player, avoiding walls
     */
    public void moveTowardsPlayerAvoidingWalls(Player player, GameObject[][] gameBoard) {
        int targetX = player.getX();
        int targetY = player.getY();
        int oldX = this.getX();
        int oldY = this.getY();
        
        // Calculate initial direction towards the player
        int deltaX = Integer.compare(targetX, oldX);
        int deltaY = Integer.compare(targetY, oldY);
        
        // If the path towards the player is blocked, choose a new random direction
        for (int attempts = 0; attempts < 4; attempts++) {
            int newX = oldX + deltaX;
            int newY = oldY + deltaY;
            
            // Check if the new position is within bounds and not blocked by a wall
            if (newX >= 0 && newX < gameBoard[0].length && newY >= 0 && newY < gameBoard.length) {
                GameObject targetCell = gameBoard[newY][newX];
                if (targetCell == null || targetCell.getTypeId() == 1) { // Check if ground or empty
                    setX(newX);
                    setY(newY);
                    return;
                }
            }
            
            // Randomly change direction if blocked
            deltaX = random.nextInt(3) - 1; // -1, 0, or 1
            deltaY = random.nextInt(3) - 1;
        }
        
        // Default to no movement if all attempts are blocked
        setX(oldX);
        setY(oldY);
    }
}
