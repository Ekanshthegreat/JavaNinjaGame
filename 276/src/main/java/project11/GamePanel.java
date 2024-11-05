package project11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel {

    private static final int TILE_SIZE = 16 * 3;
    private static final int PLAY_COLUMNS = 20;
    private static final int PLAY_ROWS = 16;

    public static int getTileSize() {
        return TILE_SIZE;
    }

    public static int getPlayColumns() {
        return PLAY_COLUMNS;
    }

    public static int getPlayRows() {
        return PLAY_ROWS;
    }

    private static final int BORDER_TILES = 1;   // 1 tile black rim
    private static final int DATA_TILES = 2;     // 2 tile space at the top for game data

    protected GameState gameState;
    private Image ninjaSprite;
    private Image groundSprite;

    private Renderer renderer;

    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.renderer = new Renderer();
        // remaining setup code
        int width = (PLAY_COLUMNS + 2 * BORDER_TILES) * TILE_SIZE;
        int height = (PLAY_ROWS + BORDER_TILES + DATA_TILES) * TILE_SIZE;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void render() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g, gameState.getGameObjects());
    }
}
