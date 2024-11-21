package project11;

/**
 * Factory for making all types of GameObjects
 */
public class GameObjectFactory {

    /**
     * Create a GameObject of the specified type
     * @param type Object type
     * @param x X coordinate
     * @param y Y coordinate
     * @return GameObject of the specified type
     */
    public GameObject createObject(int type, int x, int y) {
        switch (type) { 
            case 1:
                return new Ground(x, y, false, type); // typeId 1 for Ground
            
            case 2:
                return new Hole(x, y, 10, type); // typeId 2 for Hole
            
            case 3:
                return new BonusItem(x, y, 50, false, type); // typeId 3 for Bonus Item (JumpingShoes)
        
            case 4:
                return new Samurai(x, y, 10, type); // typeId 4 for Samurai with initial damage
            
            case 5:
                return new Player(x, y, type); // typeId 5 for Player

            case 6:
                return new Barrier(x, y, true, type); // typeId 6 for Barrier
            
            // 7 spawn, ended up not using

            case 8:
                return new MandatoryItem(x, y, 10, false, type); // typeId 8 for Mandatory Item (Key)
            
            case 9:
                return new End(x, y, false, type); // typeId 9 for End tile
            
            default:
                throw new IllegalArgumentException("Unknown GameObject type: " + type);
        }
    }
}
