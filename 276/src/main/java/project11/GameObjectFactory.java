package project11;

public class GameObjectFactory {

    public GameObject createObject(String type, int x, int y) {
        switch (type.toLowerCase()) {
            case "bush":
                return new Bush(x, y, false); // Creates a Bush with given position and not occupied
            case "barrier":
                return new Barrier(x, y); // Creates a Barrier with given position
            case "hole":
                return new Hole(x, y); // Creates a Hole with given position
            default:
                throw new IllegalArgumentException("Unknown GameObject type: " + type);
        }
    }
}
