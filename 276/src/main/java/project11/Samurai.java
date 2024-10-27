package project11;

public class Samurai extends Enemy implements Movable {
    private int positionX;
    private int positionY;

    public Samurai(int damage) {
        super(damage);
        this.positionX = 0;
        this.positionY = 0;
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage = difficulty * 2;  // Example logic, adjust as needed
    }

    @Override
    public void move(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        System.out.println("Samurai moved to position (" + x + ", " + y + ")");
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }
}


