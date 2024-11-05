package project11;

public class Samurai extends Enemy {
    public Samurai(int x, int y, int damage, int typeId) {
        super(x, y, damage, typeId);
    }

    @Override
    public void setDifficulty(int difficulty) {
        // Adjust damage or other properties based on difficulty
        this.damage += difficulty; // Example adjustment
    }


}
