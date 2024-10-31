package project11;

public class Hole extends GameObject {
    public Hole(int x, int y) {
        super(x, y, false); // Assume Hole is not solid
    }

    @Override
    public void render() {
        System.out.println("Rendering a Hole at (" + x + ", " + y + ")");
    }

    @Override
    public boolean isSolid() {
        return false; // Holes are not solid
    }
}

