package project11;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected boolean solid;

    public GameObject(int x, int y, boolean solid) {
        this.x = x;
        this.y = y;
        this.solid = solid;
    }

    // Abstract method for rendering the object
    public abstract void render();

    // Method to check if the object is solid
    public boolean isSolid() {
        return solid;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}