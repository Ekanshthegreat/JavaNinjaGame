package project11;

public abstract class Item {
    protected int x; // X position
    protected int y; // Y position
    protected int score; // Score associated with the item
    protected boolean isSpawned; // Indicates if the item is spawned

    // Constructor
    public Item(int x, int y, int score) {
        this.x = x;
        this.y = y;
        this.score = score;
        this.isSpawned = true; // Assume items are spawned upon creation
    }

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

    public abstract void onPickup(); // Abstract method to be implemented by subclasses
}
