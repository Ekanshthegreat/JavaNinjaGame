package project11;

public class CellArray {
    private Cell[][] cells;

    public CellArray(int width, int height) {
        cells = new Cell[width][height];
        initializeCells();
    }

    // Initializes each cell with default or empty GameObjects
    private void initializeCells() {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j] = new Cell(new GameObject[]{}); // Initialize empty cells by default
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < cells.length && y >= 0 && y < cells[0].length) {
            return cells[x][y];
        } else {
            throw new IndexOutOfBoundsException("Invalid cell coordinates: (" + x + ", " + y + ")");
        }
    }
}
