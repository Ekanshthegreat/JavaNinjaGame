package project11;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected boolean solid;
    protected boolean visible;

    public abstract void draw();
}