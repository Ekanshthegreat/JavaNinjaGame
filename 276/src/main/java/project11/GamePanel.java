package project11;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    // Define tile sizes
    final int tile = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = tile * scale;

    // Define game size
    final int maxColumn = 16;
    final int maxRow = 12;
    final int screenWidth = tileSize * maxColumn;
    final int screenHeight = tileSize * maxRow;

    Thread gameThread;

    /**
     * Create a game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
