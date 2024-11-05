package project11;

public abstract class Enemy extends GameObject {
    protected int damage; // Damage dealt by the enemy

    // Constructor
    public Enemy(int x, int y, int damage, int typeId) {
        super(x, y, true, typeId); // Enemies are solid, so solid = true
        this.damage = damage;
    }

    // Abstract method to set the difficulty level
    public abstract void setDifficulty(int difficulty);

    // Getter for damage
    public int getDamage() {
        return damage;
    }
}
