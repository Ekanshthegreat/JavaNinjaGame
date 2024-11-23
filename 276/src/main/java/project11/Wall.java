package project11;

/**
 * Wall class to extend GameObject
 */
public class Wall extends GameObject {
    /**
     * Make a Wall which extends GameObject
     * @param x X Coordinate of wall
     * @param y Y Coordinate of wall
     */
    public Wall(int x, int y) {
        super(x, y, true, 6);
    }

}