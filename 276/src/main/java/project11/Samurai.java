package project11;

import java.util.Random;

/**
 * Samurai class to extend Enemy
 */
public class Samurai extends Enemy {

    private int damage; // Changed from static final to instance variable

    /**
     * Make a Samurai which extends Enemy
     * @param x X Coordinate of samurai
     * @param y Y Coordinate of samurai
     */
    public Samurai(int x, int y) {
        super(x, y, Constants.getSamuraiDamage(), 4);
        // this.damage = damage; // Set the initial damage
    }

    /**
     * Set the damage value for the Samurai
     * @param damage New damage value to set
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Attack the player with the damage
     * @param player Player object to attack
     * @param gameState Current game state
     */
    public void attackPlayer(Player player, GameState gameState) {
        player.takeDamage(damage);
        System.out.println("Samurai attacked player for " + damage + " damage!");
        gameState.removeEnemy(this);
    }

    /**
     * Attempt to move towards the player, avoiding walls and passing through other objects
     * @param player Player object to move towards
     * @param gameBoard Current game board
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

    private boolean validXY(int x, int y, GameObject[][] gameBoard) {
        return x >= 0 && x < gameBoard[0].length && y >= 0 && y < gameBoard.length;
    }

    /**
     * Attempt to move in a direction, if possible
     * @param deltaX Change in X coordinate
     * @param deltaY Change in Y coordinate
     * @param gameBoard Current game board
     * @return True if move was successful, false otherwise
     */
    private boolean tryMove(int deltaX, int deltaY, GameObject[][] gameBoard) {
        int newX = getX() + deltaX;
        int newY = getY() + deltaY;

        if(!validXY(newX, newY, gameBoard))  {
            return false;
        }

        GameObject targetCell = gameBoard[newY][newX];

        // Check if the new position is passable (can pass through items, holes, but not walls)
        if (!targetCell.isSolid()) {
            setX(newX);
            setY(newY);
            return true;
        }

        return false; // Move is blocked by a wall
    }
}
