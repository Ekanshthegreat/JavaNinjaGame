package project11;

public class ConcreteCell extends Cell {
    @Override
    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public GameObject getGameObject() {
        return this.gameObject;
    }
}
