package project11;

public class End extends GameObject{
    public End(int x, int y, boolean solid, int typeId) {
        super(x, y, solid, typeId);
    }

    @Override
    public boolean isSolid() {
        return solid;
    }
}
