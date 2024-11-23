package project11;

/**
 * Barrier class to extend GameObject
 */
public class Barrier extends GameObject {
    
    /**
     * Make a barrier which extends GameObject
     * @param x X Coordinate of barrier
     * @param y Y Coordinate of barrier
     */
    public Barrier(int x, int y) {
        super(x, y, true, 6);
    }

    /**
     * Return if the object is solid
     * @return If the object is solid
     */
    @Override
    public boolean isSolid() {
        return solid;
    }

}
