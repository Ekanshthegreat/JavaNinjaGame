package project11;

/**
 * Item class used to represent the items in the game.
 * Extends GameObject class.
 * 
 * @author Felmer M
 */

public abstract class Item extends GameObject {

    private boolean pickedUp = false;
    
    /**
     * Constructor for the Item class.
     * Solid is set to false.
     * 
     * @param x initializes the x position of the item.
     * @param y initializes the y position of the item.
     */
    public Item(int x, int y) {
        this.x = x;
        this.y = y;
        this.solid = false; 
    }

    /**
     * Method to be called when the item is picked up.
     *
     * @return true if the item is picked up, false otherwise.
     */
    public boolean isPicked() {
        return pickedUp;
    }

    /**
     * Method to be called when the item is picked up.
     */
    public  void pickup(){
        pickedUp = true;
    }
}