package project11;

public abstract class Enemy extends GameObject implements Movable {
    protected int damage;

    public Enemy(int x, int y, int damage, int typeId) {
        super(x, y, true, typeId); // Assuming enemies are solid
        this.damage = damage;
    }

    public abstract void setDifficulty(int difficulty);
}
