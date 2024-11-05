package project11;

public class CellArray {
    private Cell[][] cells;

    public CellArray(int width, int height) {
        // Ensure that width is the number of columns and height is the number of rows
        cells = new Cell[height][width]; // Rows as height, Columns as width
        for (int y = 0; y < height; y++) { // Loop through rows (height)
            for (int x = 0; x < width; x++) { // Loop through columns (width)
                cells[y][x] = new Cell(); // Initialize each cell
            }
        }
    }

    // Access cell by (x, y) where x is the column (horizontal) and y is the row (vertical)
    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= getWidth() || y >= getHeight()) {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
        return cells[y][x]; // Access row first, then column
    }

    // Get the primary GameObject in a specific cell at (x, y)
    public GameObject getGameObject(int x, int y) {
        return getCell(x, y).getPrimaryObject();
    }

    // Set a GameObject in a specific cell at (x, y)
    public void setGameObject(int x, int y, GameObject obj) {
        Cell cell = getCell(x, y);
        cell.setPrimaryObject(obj);
    }

    // Clear a specific GameObject from the cell at (x, y)
    public void clearGameObject(int x, int y) {
        Cell cell = getCell(x, y);
        GameObject obj = cell.getPrimaryObject();
        if (obj != null) {
            cell.removeGameObject(obj);
        }
    }

    // Get the width (number of columns)
    public int getWidth() {
        return cells[0].length;
    }

    // Get the height (number of rows)
    public int getHeight() {
        return cells.length;
    }
}
