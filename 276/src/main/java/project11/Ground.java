package project11;

/**
 * Ground class to extend GameObject
 */
public class Ground extends GameObject {

    /**
     * Make a ground which extends GameObject
     * @param x X Coordinate of ground
     * @param y Y Coordinate of ground
     * @param solid If the object is solid
     * @param typeId Holds ground id
     */
    public Ground(int x, int y) {
        super(x, y, false, 1);
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