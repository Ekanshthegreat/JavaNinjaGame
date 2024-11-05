package project11;

public class Player extends GameObject {
    private int score = 0;
    private int health = 100;

    public Player(int x, int y, int typeId) {
        super(x, y, true, typeId);
    }

    public void increaseScore(int amount) {
        score += amount;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }
}
