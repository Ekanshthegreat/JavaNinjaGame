package project11;

public class BonusItem extends Item {
    private int vaultValue; // Additional value for vaulting if necessary

    public BonusItem(int x, int y, int score, boolean isSolid, int typeId) {
        super(x, y, score, isSolid, typeId); // Call the parent constructor
    }

    @Override
    public void onPickup() {
        // Implement the pickup logic for bonus items
        System.out.println("Picked up a Bonus Item with score: " + getScore() + " and vault value: " + vaultValue);
        // Logic for what happens when the item is picked up (e.g., add score, add vault value, etc.)
    }
}
