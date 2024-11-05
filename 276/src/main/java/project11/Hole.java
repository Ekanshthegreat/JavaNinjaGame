package project11;

public class Hole extends Enemy{

    public Hole(int x, int y, int damage, int typeId) {
        super(x, y, damage, typeId); // Pass values to the superclass constructor
    }

    @Override
    public void setDifficulty(int difficulty) {
        this.damage = damage * difficulty; // Adjust damage based on difficulty
    }

    
}
