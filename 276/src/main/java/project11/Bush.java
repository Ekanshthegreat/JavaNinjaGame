package project11;

public class Bush extends GameObject {
    private boolean occupied;

    public Bush(int x, int y, boolean occupied) {
        super(x, y, occupied);
        this.solid = false; // Bush is not solid by default
        this.occupied = occupied;
    }

    @Override
    public void render() {
        // Implement rendering logic for Bush
        System.out.println("Rendering Bush at (" + x + ", " + y + ")");
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
