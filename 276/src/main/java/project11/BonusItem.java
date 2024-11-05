package project11;

public class BonusItem extends Item {
    private int vault; // Additional property for bonus items

    public BonusItem(int x, int y, int score) {
        super(x, y, score); // Call the constructor of the Item class
    }

    @Override
    public void onPickup() {
        // Logic for what happens when the item is picked up
        System.out.println("Picked up a bonus item at (" + x + ", " + y + ") with score: " + score);
        this.isSpawned = false; // Mark the item as not spawned anymore
    }

    public void setVault(int vault) {
        this.vault = vault;
    }
}
