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

    @Override
    public void move(int targetX, int targetY) {
        // AI logic to move towards the target (player)
        if (targetX > this.x) {
            this.x++; // Move right
        } else if (targetX < this.x) {
            this.x--; // Move left
        }
        if (targetY > this.y) {
            this.y++; // Move down
        } else if (targetY < this.y) {
            this.y--; // Move up
        }
    }
}
