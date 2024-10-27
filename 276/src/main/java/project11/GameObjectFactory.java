package project11;

public class GameObjectFactory {

    public GameObject createObject(String type) {
        switch (type.toLowerCase()) {
            case "bush":
                return new Bush(0, 0, false); // Creates a Bush with default position and not occupied
            case "barrier":
                return new Barrier(0, 0); // Creates a Barrier with default position
            default:
                throw new IllegalArgumentException("Unknown GameObject type: " + type);
        }
    }
}
