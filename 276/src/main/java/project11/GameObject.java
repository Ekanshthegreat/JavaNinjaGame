package project11;

public abstract class GameObject {
    protected int x;       // X coordinate on the grid
    protected int y;       // Y coordinate on the grid
    protected boolean solid; // Indicates if the object is solid (blocks movement)
    protected int typeId;   // Unique identifier for the type of GameObject

    // Constructor to initialize the GameObject
    public GameObject(int x, int y, boolean solid, int typeId) {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.typeId = typeId;
    }

    // Method to check if the object is solid
    public boolean isSolid() {
        return solid;
    }

    // Getter for typeId
    public int getTypeId() {
        return typeId;
    }

    // Getter for X coordinate
    public int getX() {
        return x;
    }

    // Getter for Y coordinate
    public int getY() {
        return y;
    }

    // Setter for X coordinate
    public void setX(int x) {
        this.x = x;
    }

    // Setter for Y coordinate
    public void setY(int y) {
        this.y = y;
    }
}
