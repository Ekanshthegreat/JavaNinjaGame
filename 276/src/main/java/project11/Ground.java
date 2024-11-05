package project11;

import java.awt.Graphics;

public class Ground extends GameObject {
    public Ground(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}