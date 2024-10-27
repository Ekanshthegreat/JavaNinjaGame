package project11;

public class Barrier extends GameObject {
    public Barrier() {
        this.solid = true;
    }

    @Override
    public void render() {
        System.out.println("Rendering Barrier at (" + x + ", " + y + ")");
    }
}
