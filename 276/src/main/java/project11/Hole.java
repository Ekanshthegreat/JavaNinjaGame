package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Hole extends GameObject {
    private static Image holeSprite;

    public Hole(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
        this.solid = false; // Hole is not solid by default
        loadSprite();
    }

    // Load the hole sprite once
    private void loadSprite() {
        if (holeSprite == null) {
            try {
                holeSprite = ImageIO.read(getClass().getResource("/project11/sprites/hole.png"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: Could not load hole sprite.");
            }
        }
    }

    @Override
    public void render(Graphics g, int tileSize) {
        if (holeSprite != null) {
            g.drawImage(holeSprite, x * tileSize, y * tileSize, tileSize, tileSize, null);
        } else {
            // Fallback if sprite fails to load
            g.setColor(java.awt.Color.BLACK);
            g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        }
    }

    @Override
    public boolean isSolid() {
        return false;
    }
}
