package project11;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<GameObject> gameObjects; // Array of GameObjects in this cell

    public Cell() {
        this.gameObjects = new ArrayList<>();
    }

    // Check if any GameObject occupies this cell
    public boolean isOccupied() {
        return !gameObjects.isEmpty();
    }

    // Add a GameObject to this cell
    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
    }

    // Remove a GameObject from this cell
    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
    }

    // Get the list of GameObjects in this cell
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
