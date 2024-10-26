package project11;

public class ConcreteGameObject extends GameObject {
    @Override
    public void draw() {
        System.out.println("Drawing GameObject at (" + x + ", " + y + ")");
    }
}