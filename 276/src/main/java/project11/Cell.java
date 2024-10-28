package project11;

public class Cell {
    private GameObject[] gameObjects; // Array to hold multiple game objects
    private boolean occupied;

    public Cell(GameObject[] defaultGameObjects) {
        this.gameObjects = defaultGameObjects;
        this.occupied = checkOccupied();
    }

    public boolean isOccupied() {
        return occupied;
    }

    // Helper method to check if any GameObject in this cell is solid, making it "occupied"
    private boolean checkOccupied() {
        for (GameObject obj : gameObjects) {
            if (obj.isSolid()) {
                return true;
            }
        }
        return false;
    }
}
