package project11;

/**
 * End tile class to extend GameObject
 */
public class End extends GameObject{

    /**
     * Make a end which extends GameObject
     * @param x X Coordinate of end
     * @param y Y Coordinate of end
     * @param solid If the object is solid
     * @param typeId Holds end id
     */
    public End(int x, int y) {
        super(x, y, false, 9);
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
