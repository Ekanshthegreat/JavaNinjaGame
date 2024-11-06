package project11;

/**
 * Hole class to extend Enemy
 */
public class Hole extends Enemy{

    /**
     * Make a hole which extends enemy
     * @param x X Coordinate of hole
     * @param y Y Coordinate of hole
     * @param solid If the object is solid
     * @param typeId Holds hole id
     */
    public Hole(int x, int y, int damage, int typeId) {
        super(x, y, damage, typeId); // Pass values to the superclass constructor
    }

    /**
     * Return if the object is solid
     * @return If the object is solid
     */
    @Override
    public void setDifficulty(int difficulty) {
        this.damage = damage * difficulty; // Adjust damage based on difficulty
    }
    
}
