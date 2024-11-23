package project11;

/**
 * Item class to extend GameObject
 */
public abstract class Item extends GameObject {
    protected int score; // Score associated with the item
    protected boolean isSpawned; // Indicates if the item is spawned

    /**
     * Make a item which extends GameObject
     * @param x X Coordinate of Item
     * @param y Y Coordinate of Item
     * @param score How many points the item is worth
     * @param solid If the object is solid
     * @param typeId Holds item id
     */
    public Item(int x, int y, int score, boolean solid, int typeId) {
        super(x, y, solid, typeId);
        this.score = score;
        this.isSpawned = true; // Assume items are spawned upon creation
    }

    /*
     * Get the X coordinate of the item
     */
    public int getX() {
        return x;
    }
    /*
     * Get the Y coordinate of the item
     */
    public int getY() {
        return y;
    }
    /*
     * Get the score of the item
     */
    public int getScore() {
        return score;
    }
    /*
     * Get if the item is spawned
     */
    public boolean isSpawned() {
        return isSpawned;
    }
    /*
     * Set the score of the item
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method to pick up the item
     */
    public abstract void onPickup();
}
