package project11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Draw the window, and render objects and data
 */
public class GamePanel extends JPanel {

    // Game and tile constants
    private static final int TILE_SIZE = 16 * 3;
    private static final int PLAY_COLUMNS = 10;
    private static final int PLAY_ROWS = 8;

    public static int getTileSize() {
        return TILE_SIZE;
    }
    public static int getPlayColumns() {
        return PLAY_COLUMNS;
    }
    public static int getPlayRows() {
        return PLAY_ROWS;
    }

    // Tile constants for border and data space
    private static final int BORDER_TILES = 1; // space around the play area
    private static final int DATA_TILES = 2; // space above the play area

    public static int getBorderTiles() {
        return BORDER_TILES;
    }
    public static int getDataTiles() {
        return DATA_TILES;
    }

    protected GameState gameState;
    private Renderer renderer;

    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.renderer = new Renderer();
        
        // Set dimensions based on tile size, borders, and data space
        int width = (PLAY_COLUMNS + 2*BORDER_TILES) * TILE_SIZE;
        int height = (PLAY_ROWS + 2*BORDER_TILES + DATA_TILES) * TILE_SIZE;
        
        // Set window properties
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    

    public void render() {
        // repaint the panel on every call
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, gameState.getGameObjects());

        // Draw the player's score in the data tiles area
        int score = gameState.getPlayer().getScore(); // Get the player's score
        int tileSize = GamePanel.getTileSize();
        
        // Set the color and font for the score display
        g.setColor(Color.white); // Set text color to white
        g.drawString("Score: " + score, 10, tileSize); // Draw score in the data area
    }
}
