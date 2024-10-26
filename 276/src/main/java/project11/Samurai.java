package project11;

public class Samurai extends Enemy {
    @Override
    public void render() {
        System.out.println("Rendering Samurai at (" + x + ", " + y + ")");
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage = difficulty * 20;
        System.out.println("Samurai difficulty set. Damage: " + this.damage);
    }
}
