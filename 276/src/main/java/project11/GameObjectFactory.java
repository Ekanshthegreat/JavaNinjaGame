package project11;

public class GameObjectFactory {

    public GameObject createObject(String type, int x, int y) {
        switch (type.toLowerCase()) {
            case "barrier":
                return new Barrier(x, y, true, 6); // typeId for Barrier
            case "hole":
                return new Hole(x, y, 5, 3); // typeId for Hole with initial damage
            case "samurai":
                return new Samurai(x, y, 10, 4); // typeId for Samurai with initial damage
            case "player":
                return new Player(x, y); // Creating a Player
            
            // Creating a mandatory item
            case "mandatoryitem":
                return new MandatoryItem(x, y, 10, false); // Example values for score and isSpawned
            
            // Creating a bonus item
            case "bonusitem":
                return new BonusItem(x, y, 20); // Example value for score
            
            default:
                throw new IllegalArgumentException("Unknown GameObject type: " + type);
        }
    }
}
