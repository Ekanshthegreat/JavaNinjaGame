package project11;

/**
 * Represents a ground tile in the game.
 */
public class Ground extends GameObject {

    // Constructor for a ground tile, setting its position and typeId
    public Ground(int x, int y) {
        super(x, y, false, 1); // '1' as typeId for ground; adjust if needed
    }
    
}