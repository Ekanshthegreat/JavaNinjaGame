package project11;

public class GameObjectFactory {

    public GameObject createObject(String type, int x, int y) {
        switch (type.toLowerCase()) {
            case "hole":
                return new Hole(x, y, 10, 2); // typeId 2 for Hole
            
            case "barrier":
                return new Barrier(x, y, true, 6); // typeId 6 for Barrier
            
            case "samurai":
                return new Samurai(x, y, 10, 4); // typeId 4 for Samurai with initial damage
            
            case "player":
                return new Player(x, y, 5); // typeId 5 for Player
            
            case "mandatoryitem":
                return new MandatoryItem(x, y, 10, false, 8); // typeId 8 for Mandatory Item (Key)
            
            case "ground":
                return new Ground(x, y, false, 1); // typeId 1 for Ground
            
            case "bonusitem":
                return new BonusItem(x, y, 10, false, 3); // typeId 3 for Bonus Item (JumpingShoes)
            
            
            default:
                throw new IllegalArgumentException("Unknown GameObject type: " + type);
        }
    }
}
