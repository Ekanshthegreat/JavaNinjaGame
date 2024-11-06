package project11;

/**
 * Enemy class to extend GameObject
 */
public abstract class Enemy extends GameObject {
    // How much damage the enemy does
    protected int damage;

    /**
     * Make an enemy which extends GameObject
     * @param x X Coordinate of Enemy
     * @param y Y Coordinate of Enemy
     * @param solid If the object is solid
     * @param typeId Holds enemy id
     */
    public Enemy(int x, int y, int damage, int typeId) {
        super(x, y, true, typeId);
        this.damage = damage;
    }

    public abstract void setDifficulty(int difficulty);
    
    // Public getters and setters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getDamage() {
        return damage;
    }

    /**
     * Moves the enemy towards the player depending on the players position and obstacle collision
     * @param player Player object
     */
    public void moveTowardsPlayer(Player player) {
        int playerX = player.getX();
        int playerY = player.getY();

        // Move enemy towards player
        if (this.x < playerX) {
            this.x++; // Move right
        } else if (this.x > playerX) {
            this.x--; // Move left
        }

        if (this.y < playerY) {
            this.y++; // Move down
        } else if (this.y > playerY) {
            this.y--; // Move up
        }
    }
}
