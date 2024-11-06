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
    public End(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
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
