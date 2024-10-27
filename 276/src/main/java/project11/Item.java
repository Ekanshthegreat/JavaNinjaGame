package project11;

/**
 * Item class used to represent the items in the game.
 * Extends GameObject class.
 * 
 */
public abstract class Item extends GameObject {
    public  Item(int x, int y) {
        this.x = x;
        this.y = y;
        this.solid = false;
    }

    @Override
    public abstract void render();
}
