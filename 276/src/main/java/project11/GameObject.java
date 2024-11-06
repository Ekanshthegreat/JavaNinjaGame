package project11;

/**
 * Abstract GameObject class, used by all types of objects
 */
public abstract class GameObject {
    // Local variables
    protected int x;
    protected int y;
    protected boolean solid;
    protected int typeId;

    /**
     * Make a GameObject
     * @param x X Coordinate of GameObject
     * @param y Y Coordinate of GameObject
     * @param solid If the object is solid
     * @param typeId Holds GameObject id
     */
    public GameObject(int x, int y, boolean solid, int typeId) {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.typeId = typeId;
    }

    /**
     * Function for checking if an object is solid
     * @return If the object is solid
     */
    public boolean isSolid() {
        return solid;
    }


    // Public Getters
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
