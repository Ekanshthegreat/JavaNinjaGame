package project11;

/**
 * Hole class to extend Enemy
 */
public class Hole extends Enemy{

    /**
     * Make a hole which extends enemy
     * @param x X Coordinate of hole
     * @param y Y Coordinate of hole
     */
    public Hole(int x, int y) {
        super(x, y, Constants.getHoleDamage(), 2);
    }

    /**
     * Set the difficulty of the hole
     */
    @Override
    public void setDifficulty(int difficulty) {
        this.damage = damage * difficulty;
    }
    
}
