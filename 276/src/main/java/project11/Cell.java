package project11;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<GameObject> gameObjects;
    private GameObject primaryObject;

    public Cell() {
        this.gameObjects = new ArrayList<>();
    }

    public boolean isOccupied() {
        return primaryObject != null && primaryObject.isSolid();
    }

    public void addGameObject(GameObject obj) {
        gameObjects.add(obj);
        if (primaryObject == null || !primaryObject.isSolid()) {
            primaryObject = obj;
        }
    }

    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
        if (primaryObject == obj) {
            primaryObject = gameObjects.isEmpty() ? null : gameObjects.get(0);
        }
    }

    public GameObject getPrimaryObject() {
        return primaryObject;
    }

    public void setPrimaryObject(GameObject obj) {
        primaryObject = obj;
        if (!gameObjects.contains(obj)) {
            gameObjects.add(obj);
        }
    }
}
