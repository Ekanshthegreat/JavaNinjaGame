package project11;

/**
 * BonusItem class to extend Item
 */
public class BonusItem extends Item {

    /**
     * Make a bonus item which extends Item
     * @param x X Coordinate of item
     * @param y Y Coordinate of item
     */
    public BonusItem(int x, int y) {
        super(x, y, Constants.getBonusScore(), false, 3);
    }

    /**
     * onPickup function for picking up items.
     */
    @Override
    public void onPickup() {
        System.out.println("Picked up a Bonus Item with score: " + getScore());
    }
}
