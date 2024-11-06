package project11;

public class Player extends GameObject {
    private int score = 50; // default score

    public Player(int x, int y, int typeId) {
        super(x, y, true, typeId);
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void takeDamage(int damage) {
        score -= damage; // Deduct from score instead of health
        if (score < 0) {
            score = 0; // Ensure score doesn't drop below zero
        }
    }

    public int getScore() {
        return score;
    }
}
