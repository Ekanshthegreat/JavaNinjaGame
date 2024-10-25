package project11;

public class CharacterThread extends BaseThread {
    private final GameState gameState;

    public CharacterThread(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void tick() {
        System.out.println("Character is moving based on user input");
        
    }
}
