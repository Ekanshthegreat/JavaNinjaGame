package project11;

public class Player extends GameObject{
    // Player attributes like position, score, etc.
    private int score;

    public Player(int x, int y, int typeId) {
        super(x, y, true, typeId);
    }

    public void pickupItem(Pickupable item) {
        item.onPickup(); // Call onPickup to handle the item pickup logic
        if (item instanceof Item) {
            score += ((Item) item).getScore(); // Increase score based on the item's score
        }
    }

    public int getScore() {
        return score;
    }
}