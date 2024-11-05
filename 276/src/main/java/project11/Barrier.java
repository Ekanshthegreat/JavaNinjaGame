package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Barrier extends GameObject {
    private static Image wallSprite;

    public Barrier(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
    }

    

    

    @Override
    public boolean isSolid() {
        return solid;
    }
}
