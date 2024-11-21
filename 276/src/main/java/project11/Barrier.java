package project11;

/**
 * Barrier class to extend GameObject
 */
public class Barrier extends GameObject {
    
    /**
     * Make a barrier which extends GameObject
     * @param x X Coordinate of barrier
     * @param y Y Coordinate of barrier
     * @param solid If the object is solid
     * @param typeId Holds barrier id
     */
    public Barrier(int x, int y) {
        super(x, y, true, 6);
        // this.solid = true; // Barrier is solid by default
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
