package project11;

public class CellArray {
    private Cell[][] cells;

    // Initialize the cell array with given dimensions
    public CellArray(int width, int height) {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(); // Initialize each cell
            }
        }
    }

    // Get a specific cell at position (x, y)
    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= cells.length || y >= cells[0].length) {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
        return cells[x][y];
    }
}
