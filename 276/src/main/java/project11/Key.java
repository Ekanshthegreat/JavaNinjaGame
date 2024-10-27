package project11;

/**
 * Key class used to represent the key in the game.
 * Extends Item class.
 * 
 * @author Felmer
 */
public class Key extends Item {
    /**
     * Constructor for the Key class.
     * Solid is set to false.
     * 
     * @param x initializes the x position of the key.
     * @param y initializes the y position of the key.
     */
    public Key(int x, int y) {
        super(x, y);
        this.solid = false;
    }

    /**
     * Renders the key.
     */
    public void render() {
        System.out.println("Key rendered at: " + x + ", " + y);
        //TODO: render the key
        throw new UnsupportedOperationException("Render method not implemented yet.");
    }

    
}
