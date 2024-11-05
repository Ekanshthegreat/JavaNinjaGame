package project11;

public class Player extends GameObject implements Movable {
    private int health;
    private Pickupable[] inventory;
    private boolean canVault;

    public Player(int x, int y) {
        super(x, y, false, 1); // Assuming Player is not solid by default, with typeId 1
        this.health = 100; 
        this.inventory = new Pickupable[10]; 
        this.canVault = false;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            death();
        }
    }

    public void death() {
        System.out.println("Player has died.");
        // Handle player death logic
    }

    @Override
    public void move(int deltaX, int deltaY) {
        // Implement movement logic for the player
        this.x += deltaX; // Update player's x position
        this.y += deltaY; // Update player's y position
        System.out.println("Moving player to (" + this.x + ", " + this.y + ")");
    }

    public void movePlayer(String direction) {
        switch (direction.toLowerCase()) {
            case "up":
                move(0, -1); // Move up
                break;
            case "down":
                move(0, 1); // Move down
                break;
            case "left":
                move(-1, 0); // Move left
                break;
            case "right":
                move(1, 0); // Move right
                break;
            default:
                System.out.println("Invalid direction");
        }
    }
}
