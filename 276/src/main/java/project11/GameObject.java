package project11;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected boolean solid;

    public abstract void render();
    public boolean isSolid() {
        return solid;
    }
}