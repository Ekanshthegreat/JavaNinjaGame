package project11;

public class Player {
    // Player attributes like position, score, etc.
    private int score;

    public void pickupItem(Pickupable item) {
        item.onPickup(); // Call onPickup to handle the item pickup logic
        // You may also want to adjust the player's score based on the item
        if (item instanceof Item) {
            score += ((Item) item).getScore(); // Increase score based on the item's score
        }
    }

    public int getScore() {
        return score;
    }
}
