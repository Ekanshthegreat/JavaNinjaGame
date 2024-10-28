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
    private static final int TILE_SIZE = 16 * 3; // Scaled tile size
    private static final int PLAY_COLUMNS = 20;  // Number of tiles in the horizontal play area
    private static final int PLAY_ROWS = 16;     // Number of tiles in the vertical play area
    private static final int BORDER_TILES = 1;   // 1 tile black rim
    private static final int DATA_TILES = 2;     // 2 tile space at the top for game data

    protected GameState gameState;
    private Image ninjaSprite;
    private Image groundSprite;

    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        int width = (PLAY_COLUMNS + 2 * BORDER_TILES) * TILE_SIZE;
        int height = (PLAY_ROWS + BORDER_TILES + DATA_TILES) * TILE_SIZE;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        loadSprite();
    }

    // Load sprites from the resources folder
    private void loadSprite() {
        try {
            ninjaSprite = ImageIO.read(getClass().getResource("/project11/sprites/Samurai.png"));
            groundSprite = ImageIO.read(getClass().getResource("/project11/sprites/Ground.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load sprites");
        }
    }
    

    public void render() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

         // Draw the play area background using ground tiles
        for (int row = 0; row < PLAY_ROWS; row++) {
            for (int col = 0; col < PLAY_COLUMNS; col++) {
                int x = BORDER_TILES * TILE_SIZE + col * TILE_SIZE;
                int y = DATA_TILES * TILE_SIZE + row * TILE_SIZE;

                if (groundSprite != null) {
                    g2d.drawImage(groundSprite, x, y, TILE_SIZE, TILE_SIZE, null);
                } else {
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.fillRect(x, y, TILE_SIZE, TILE_SIZE);
                }
            }
        }

        // Check if the sprite was successfully loaded
        if (ninjaSprite != null) {
            g2d.drawImage(
                ninjaSprite,
                BORDER_TILES * TILE_SIZE + gameState.getPlayerX(),
                ((DATA_TILES + (PLAY_ROWS / 2) - 1) * TILE_SIZE) + gameState.getPlayerY(),
                TILE_SIZE, TILE_SIZE,
                null
            );
        } else {
            // Fallback to a white box if the sprite doesn't load
            g2d.setColor(Color.white);
            g2d.fillRect(
                BORDER_TILES * TILE_SIZE + gameState.getPlayerX(),
                DATA_TILES * TILE_SIZE + gameState.getPlayerY(),
                TILE_SIZE, TILE_SIZE
            );
        }

        g2d.dispose();
    }
}
