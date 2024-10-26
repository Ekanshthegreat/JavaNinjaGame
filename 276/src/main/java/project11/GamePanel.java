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

    protected GameState gameState;
    private Image ninjaSprite;

    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.setPreferredSize(new Dimension(800, 600));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        loadSprite();
    }

    // Load the ninja sprite
    private void loadSprite() {
        try {
            // Load the image as a resource from the sprites folder within project11
            ninjaSprite = ImageIO.read(getClass().getResource("/project11/sprites/ninja.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Could not load ninja sprite.");
        }
    }
    

    public void render() {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Check if the sprite was successfully loaded
        if (ninjaSprite != null) {
            g2d.drawImage(ninjaSprite, gameState.getPlayerX(), gameState.getPlayerY(), TILE_SIZE, TILE_SIZE, null);
        } else {
            // Fallback to a white box if the sprite doesn't load
            g2d.setColor(Color.WHITE);
            g2d.fillRect(gameState.getPlayerX(), gameState.getPlayerY(), TILE_SIZE, TILE_SIZE);
        }

        g2d.dispose();
    }
}
