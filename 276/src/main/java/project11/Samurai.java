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
        gameState.removeEnemy(this);
    }

    /**
     * Attempt to move towards the player, avoiding walls and passing through other objects
     */
    public void moveTowardsPlayerAvoidingWalls(Player player, GameObject[][] gameBoard) {
        int targetX = player.getX();
        int targetY = player.getY();
        int oldX = this.getX();
        int oldY = this.getY();

        int deltaX = Integer.compare(targetX, oldX);
        int deltaY = Integer.compare(targetY, oldY);

        if (tryMove(deltaX, deltaY, gameBoard)) return;

        int[][] alternateMoves = {
            {deltaX, 0},
            {0, deltaY},
            {-deltaX, deltaY},
            {deltaX, -deltaY}
        };

        for (int[] move : alternateMoves) {
            if (tryMove(move[0], move[1], gameBoard)) return;
        }
    }

    private boolean tryMove(int deltaX, int deltaY, GameObject[][] gameBoard) {
        int newX = getX() + deltaX;
        int newY = getY() + deltaY;

        if (newX < 0 || newX >= gameBoard[0].length || newY < 0 || newY >= gameBoard.length) {
            return false;
        }

        GameObject targetCell = gameBoard[newY][newX];

        // Check if the new position is passable (can pass through items, holes, but not walls)
        if (targetCell == null || targetCell.getTypeId() == 1 || targetCell.getTypeId() == 2 || targetCell.getTypeId() == 3 || targetCell.getTypeId() == 8) {
            setX(newX);
            setY(newY);
            return true;
        }

        return false; // Move is blocked by a wall
    }
}
