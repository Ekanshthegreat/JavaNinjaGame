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
     * @param typeId Holds player id
     */
    public Player(int x, int y, int typeId) {
        super(x, y, true, typeId);
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void takeDamage(int damage) {
        score -= damage;
         // Ensure score doesn't drop below zero
        if (score < 0) {
            score = 0;
        }
    }

    public int getScore() {
        return score;
    }
}
