package project11;

public abstract class Enemy {
    protected int damage;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public abstract void setDifficulty(int difficulty);

    public int getDamage() {
        return damage;
    }
}

