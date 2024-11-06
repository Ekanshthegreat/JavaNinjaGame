package project11;

import java.util.Random;

/**
 * Samurai class to extend Enemy
 */
public class Samurai extends Enemy {

    private static final int DAMAGE = 20;
    private Random random = new Random();
    private String lastCellKey;

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

        // Calculate direction towards the player
        int deltaX = Integer.compare(targetX, oldX);
        int deltaY = Integer.compare(targetY, oldY);

        // Try moving directly towards the player first
        if (tryMove(deltaX, deltaY, gameBoard)) return;

        // If blocked, try other directions
        int[][] alternateMoves = {
            {deltaX, 0},   // Horizontal
            {0, deltaY},   // Vertical
            {-deltaX, deltaY}, // Opposite horizontal
            {deltaX, -deltaY}  // Opposite vertical
        };

        for (int[] move : alternateMoves) {
            if (tryMove(move[0], move[1], gameBoard)) return;
        }
    }

    /**
     * Attempt to move in the specified direction and save/restore cells
     * @param deltaX X-axis movement direction
     * @param deltaY Y-axis movement direction
     * @param gameBoard The game board
     * @return true if movement was successful, false if blocked by a wall
     */
    private boolean tryMove(int deltaX, int deltaY, GameObject[][] gameBoard) {
        int newX = getX() + deltaX;
        int newY = getY() + deltaY;

        // Check if the new position is within bounds
        if (newX < 0 || newX >= gameBoard[0].length || newY < 0 || newY >= gameBoard.length) {
            return false;
        }

        GameObject targetCell = gameBoard[newY][newX];

        // Check if the new position is a wall and block movement if it is
        if (targetCell != null && targetCell.getTypeId() == 6) { // Wall typeId is 6
            return false;
        }

        // Save the current cell's content before moving
        if (lastCellKey != null) {
            String[] parts = lastCellKey.split(",");
            int lastX = Integer.parseInt(parts[0]);
            int lastY = Integer.parseInt(parts[1]);
            gameBoard[lastY][lastX] = targetCell;
        }

        lastCellKey = getX() + "," + getY();

        // Update Samurai's position
        setX(newX);
        setY(newY);
        gameBoard[newY][newX] = this;

        return true;
    }
}
