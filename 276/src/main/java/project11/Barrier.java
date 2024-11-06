package project11;

import java.awt.Graphics;

public class Barrier extends GameObject {
    
    public Barrier(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
        this.solid = true; // Barrier is solid by default
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    // Render the Barrier using the provided graphics object and Renderer class
    // public void render(Graphics g, Renderer renderer, int tileSize) {
    //     g.drawImage(renderer.getWallSprite(), x * tileSize, y * tileSize, tileSize, tileSize, null);
    // }
}
