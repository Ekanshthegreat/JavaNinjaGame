package project11;

/**
 * Player class to extend GameObject
 */
public class Player extends GameObject {
    // Default score value
    private int score = 50;

    /**
     * Make a player which extends GameObject
     * @param x X Coordinate of player
     * @param y Y Coordinate of player
     */
    public Player(int x, int y) {
        super(x, y, true, 5);
    }

    /**
     * Increase the score of the player
     * @param amount Amount to increase score by
     */
    public void increaseScore(int amount) {
        score += amount;
    }

    /**
     * Decrease the score of the player
     * @param damage Amount of damage to take
     */
    public void takeDamage(int damage) {
        score -= damage;
         // Ensure score doesn't drop below zero
        if (score < 0) {
            score = 0;
        }
    }

    /**
     * Get the score of the player
     * @return score
     */
    public int getScore() {
        return score;
    }
}
