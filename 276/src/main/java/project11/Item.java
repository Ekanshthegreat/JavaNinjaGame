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
        super(x, y, solid, typeId); // Assuming typeId is not needed or can be set to a default value
        this.score = score;
        this.isSpawned = true; // Assume items are spawned upon creation
    }

    // Getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getScore() {
        return score;
    }
    public boolean isSpawned() {
        return isSpawned;
    }
    public void setScore(int score) {
        this.score = score;
    }

    // Abstract method to be implemented by subclasses
    public abstract void onPickup();
}
