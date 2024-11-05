package project11;

import java.util.List;

public class CellArray {
    private Cell[][] cells;

    public CellArray(int width, int height) {
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(); // Initialize each cell
            }
        }
    }

    public Cell getCell(int x, int y) {
        if (x < 0 || y < 0 || x >= cells.length || y >= cells[0].length) {
            throw new IndexOutOfBoundsException("Cell position out of bounds");
        }
        return cells[x][y];
    }

    // Mimics getting a single GameObject by coordinates, for compatibility
    public GameObject getGameObject(int x, int y) {
        return getCell(x, y).getPrimaryObject();
    }

    // Mimics setting a GameObject directly in the cell
    public void setGameObject(int x, int y, GameObject obj) {
        Cell cell = getCell(x, y);
        cell.setPrimaryObject(obj);
    }

    // Mimics clearing a specific GameObject from the cell
    public void clearGameObject(int x, int y) {
        Cell cell = getCell(x, y);
        GameObject obj = cell.getPrimaryObject();
        if (obj != null) {
            cell.removeGameObject(obj);
        }
    }

    public int getWidth() {
        return cells.length;
    }

    public int getHeight() {
        return cells[0].length;
    }
}
