package project11;

/**
 * ManditoryItem class to extend Item
 */
public class MandatoryItem extends Item {
    
    /**
     * Make a mandatory item which extends item
     * @param x X Coordinate of mandatory item
     * @param y Y Coordinate of mandatory item
     */
    public MandatoryItem(int x, int y) {
        super(x, y, Constants.getItemScore(), false, 8); // Call the parent constructor
    }

    /**
     * On pickup method
     */
    @Override
    public void onPickup() {
        System.out.println("Picked up a Mandatory Item with score: " + getScore());
    }
    
}