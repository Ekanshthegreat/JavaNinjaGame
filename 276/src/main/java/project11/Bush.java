package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bush extends GameObject {
    private boolean occupied;
    private static Image bushSprite;

    public Bush(int x, int y, boolean occupied, int typeId) {
        super(x, y, false, typeId); // Pass x, y, solid=false, and the typeId to the superclass
        this.occupied = occupied;
        loadSprite();
    }

    // Load the bush sprite once
    private void loadSprite() {
        if (bushSprite == null) {
            try {
                bushSprite = ImageIO.read(getClass().getResource("/project11/sprites/Bush.png"));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error: Could not load bush sprite.");
            }
        }
    }

    @Override
    public void render(Graphics g, int tileSize) {
        if (bushSprite != null) {
            g.drawImage(bushSprite, x * tileSize, y * tileSize, tileSize, tileSize, null);
        } else {
            // Fallback if sprite fails to load
            g.setColor(java.awt.Color.GREEN);
            g.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
        }
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
