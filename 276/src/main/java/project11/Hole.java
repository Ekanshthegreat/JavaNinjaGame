package project11;

public class Hole extends Enemy {
    @Override
    public void render() {
        System.out.println("Rendering Hole at (" + x + ", " + y + ")");
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage = difficulty * 10;
        System.out.println("Hole difficulty set. Damage: " + this.damage);
    }
}
