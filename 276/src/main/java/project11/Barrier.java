package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Barrier extends GameObject {
    private static Image wallSprite;

    public Barrier(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
        this.solid = true; // Barrier is solid by default
        loadSprite();
    }

    // Load the wall sprite once
    private void loadSprite() {
        if (wallSprite == null) {
            try {
                wallSprite = ImageIO.read(getClass().getResource("/project11/sprites/Wall.png"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: Could not load wall sprite.");
            }
        }
    }

    @Override
    public void render(Graphics g, int tileSize) {
        if (wallSprite != null) {
            g.drawImage(wallSprite, x * tileSize, y * tileSize, tileSize, tileSize, null);
        } else {
            // Fallback if sprite fails to load
            g.setColor(java.awt.Color.GRAY);
            g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        }
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}
