package project11;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Hole extends GameObject {
    private static Image holeSprite;

    public Hole(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
    }

    

    

    @Override
    public boolean isSolid() {
        return false;
    }
}
