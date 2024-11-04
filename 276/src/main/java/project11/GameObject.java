package project11;

import java.awt.Graphics;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected boolean solid;
    protected int typeId; // Unique identifier for each type of GameObject

    public GameObject(int x, int y, boolean solid, int typeId) {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.typeId = typeId;
    }

    // Abstract render method to be implemented by subclasses
    public abstract void render(Graphics g, int tileSize);

    public boolean isSolid() {
        return solid;
    }

    public int getTypeId() {
        return typeId;
    }

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
}
