package project11;

public class EnemyThread extends BaseThread {
    private final GameState gameState;

    public EnemyThread(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void tick() {
        System.out.println("Enemies are moving towards the player");

    }
}
