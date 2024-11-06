package project11;

/**
 * Samurai class to extend Enemy
 */
public class Samurai extends Enemy {

    /**
     * Make a Samurai which extends Enemy
     * @param x X Coordinate of samurai
     * @param y Y Coordinate of samurai
     * @param damage How much damage the samurai does
     * @param typeId Holds samurai id
     */
    public Samurai(int x, int y, int damage, int typeId) {
        super(x, y, damage, typeId);
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage += difficulty;
    }

}
