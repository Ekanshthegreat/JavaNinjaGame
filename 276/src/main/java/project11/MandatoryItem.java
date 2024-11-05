package project11;

public class MandatoryItem extends Item {
    // Additional fields for MandatoryItem can be added here if needed

    public MandatoryItem(int x, int y, int score, boolean isSolid, int typeId) {
        super(x, y, score, isSolid, typeId); // Call the parent constructor
    }

    @Override
    public void onPickup() {
        // Implement the pickup logic for mandatory items
        System.out.println("Picked up a Mandatory Item with score: " + getScore());
        // Logic for what happens when the item is picked up (e.g., add score, etc.)
    }
}
