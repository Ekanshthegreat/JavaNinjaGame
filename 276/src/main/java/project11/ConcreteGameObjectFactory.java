package project11;

public class ConcreteGameObjectFactory extends GameObjectFactory {
    @Override
    public GameObject createGameObject() {
        return new ConcreteGameObject();
    }
}