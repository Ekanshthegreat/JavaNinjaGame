package project11;

public class Wall extends GameObject {
    // Constructor for a wall tile, setting its position and typeId
    public Wall(int x, int y) {
        super(x, y, true, 6); // '2' as typeId for wall; adjust if needed
    }

}
