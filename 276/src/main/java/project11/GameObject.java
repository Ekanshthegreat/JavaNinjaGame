package project11;

/**
 * Abstract GameObject class, used by all types of objects
 */
public abstract class GameObject {
    /**
     * X coordinates of the object
     */
    protected int x;
    /**
     * Y coordinates of the object
     */
    protected int y;
    /**
     * If the object is solid
     */
    protected boolean solid;
    /**
     * Holds the type of the object
     */
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


    /**
     * Get the type of the object
     * @return typeId
     */
    public int getTypeId() {
        return typeId;
    }
    /**
     * Get X value
     * @return x X value
     */
    public int getX() {
        return x;
    }
    /**
     * Get Y value
     * @return y Y value
     */
    public int getY() {
        return y;
    }
    /**
     * Set X value
     * @param x X value
     */
    public void setX(int x) {
        this.x = x;
    }
    /**
     * Set Y value
     * @param y Y value
     */
    public void setY(int y) {
        this.y = y;
    }
}