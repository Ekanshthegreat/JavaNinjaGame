package project11;

public class Bush extends GameObject {
    private boolean occupied;

    public Bush() {
        this.solid = false;
    }

    @Override
    public void render() {
        System.out.println("Rendering Bush at (" + x + ", " + y + ")");
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
