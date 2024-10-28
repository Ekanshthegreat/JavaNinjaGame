package project11;

public class Barrier extends GameObject {

    public Barrier(int x, int y) {
        super(x, y, true);
        this.solid = true; // Barrier is solid by default
    }

    @Override
    public void render() {
        // Implement rendering logic for Barrier
        System.out.println("Rendering Barrier at (" + x + ", " + y + ")");
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}
