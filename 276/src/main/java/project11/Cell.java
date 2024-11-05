package project11;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<GameObject> gameObjects; // List of all objects in the cell
    private GameObject primaryObject; // Primary object in the cell for easy access

    public Cell() {
        this.gameObjects = new ArrayList<>();
    }

    // Check if the cell is occupied by any solid object
    public boolean isOccupied() {
        return primaryObject != null && primaryObject.isSolid();
    }

    // Add a GameObject to this cell and set as primary if not already occupied
    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
        if (primaryObject == null || !primaryObject.isSolid()) {
            primaryObject = obj; // Set as primary if cell is not occupied
        }
    }

    // Remove a GameObject from this cell and update the primary object if needed
    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
        if (primaryObject == obj) {
            primaryObject = gameObjects.isEmpty() ? null : gameObjects.get(0);
        }
    }

    // Get the list of all GameObjects in this cell
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    // Get the primary GameObject in this cell
    public GameObject getPrimaryObject() {
        return primaryObject;
    }

    // Set the primary object manually if needed
    public void setPrimaryObject(GameObject obj) {
        primaryObject = obj;
        if (!gameObjects.contains(obj)) {
            gameObjects.add(obj);
        }
    }
}
