package project11;

public class GameThread extends BaseThread {
    private final GameState gameState;

    public GameThread(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void tick() {
        System.out.println("Game logic is running");
        
    }
}
