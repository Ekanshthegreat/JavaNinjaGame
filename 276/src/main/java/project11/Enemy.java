package project11;

public abstract class Enemy extends GameObject {
    protected int damage;

    public Enemy(int x, int y, int damage, int typeId) {
        super(x, y, true, typeId); // Assuming enemies are solid
        this.damage = damage;
    }

    public abstract void setDifficulty(int difficulty);
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int getDamage() {
        return damage;
    }
}
