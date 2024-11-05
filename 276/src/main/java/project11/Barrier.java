package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Barrier extends GameObject {
    private static Image BarrierSprite;

    public Barrier(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
        this.solid = true; // Barrier is solid by default
        loadBarrierSprite();
    }

    private void loadBarrierSprite() {
        if (BarrierSprite == null) {
            try {
                BarrierSprite = ImageIO.read(getClass().getResource("/path/to/Barrier.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public boolean isSolid() {
        return solid;
    }
}
