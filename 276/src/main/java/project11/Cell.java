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
        if (this.primaryObject == null || !this.primaryObject.isSolid()) {
            this.primaryObject = obj;
        }
    }

    public void removeGameObject(GameObject obj) {
        gameObjects.remove(obj);
        if (this.primaryObject == obj) {
            this.primaryObject = gameObjects.isEmpty() ? null : gameObjects.get(0);
        }
    }

    public GameObject getPrimaryObject() {
        return this.primaryObject;
    }

    public void setPrimaryObject(GameObject obj) {
        primaryObject = obj;
        if (!gameObjects.contains(obj)) {
            gameObjects.add(obj);
        }
    }
}
