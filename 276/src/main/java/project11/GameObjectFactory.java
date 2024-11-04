package project11;

public class GameObjectFactory {

    public GameObject createObject(String type, int x, int y) {
        switch (type.toLowerCase()) {
            case "bush":
                return new Bush(x, y, false, 1); // typeId 1 for Bush
            case "barrier":
                return new Barrier(x, y, true, 2); // typeId 2 for Barrier
            case "hole":
                return new Hole(x, y, false, 3); // typeId 3 for Hole
            default:
                throw new IllegalArgumentException("Unknown GameObject type: " + type);
        }
    }
}
