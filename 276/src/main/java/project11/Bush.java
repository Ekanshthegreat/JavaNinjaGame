package project11;

public class Bush extends GameObject {
    private boolean occupied;

    public Bush(int x, int y, boolean occupied, int typeId) {
        super(x, y, false, typeId);
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}