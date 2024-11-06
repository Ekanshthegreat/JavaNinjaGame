package project11;

public abstract class Item extends GameObject {
    protected int score; // Score associated with the item
    protected boolean isSpawned; // Indicates if the item is spawned

    // Constructor
    public Item(int x, int y, int score, boolean isSolid, int typeId) {
        super(x, y, isSolid, typeId); // Assuming typeId is not needed or can be set to a default value
        this.score = score;
        this.isSpawned = true; // Assume items are spawned upon creation
    }

    public int getX() {
        return x; // Inherited from GameObject
    }

    public int getY() {
        return y; // Inherited from GameObject
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

    public abstract void onPickup(); // Abstract method to be implemented by subclasses
}
