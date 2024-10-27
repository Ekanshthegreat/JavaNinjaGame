package project11;

public class Hole extends Enemy {
    public Hole(int damage) {
        super(damage);
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage = difficulty * 3;  // Example logic, adjust as needed
    }
}

