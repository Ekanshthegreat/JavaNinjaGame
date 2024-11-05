package project11;

public class Player implements Movable {
    private int health;
    private Pickupable[] inventory;
    private boolean canVault;

    public Player() {
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

    public void movePlayer(String direction) {
        switch (direction.toLowerCase()) {
            case "up":
                move(0, -1);
                break;
            case "down":
                move(0, 1);
                break;
            case "left":
                move(-1, 0);
                break;
            case "right":
                move(1, 0);
                break;
            default:
                System.out.println("Invalid direction");
        }
    }

    @Override
    public void move(int x, int y) {
        // Implement movement logic for the player
        System.out.println("Moving player to (" + x + ", " + y + ")");
        // Update player position
    }
}
