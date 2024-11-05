package project11;

public class MandatoryItem extends Item {

    public MandatoryItem(int x, int y, int score) {
        super(x, y, score); // Call the constructor of the Item class
    }

    @Override
    public void onPickup() {
        // Logic for what happens when the item is picked up
        System.out.println("Picked up a mandatory item at (" + x + ", " + y + ") with score: " + score);
        this.isSpawned = false; // Mark the item as not spawned anymore
    }
}
