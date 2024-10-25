package project11;

/**
 * Main class to run the Game.
 */
public class Main{
    public static void main(String[] args) {
        System.out.println("Test!");

        // Create the window
        Window window = new Window();

        GameState gameState = new GameState();

        // Initialize and run each thread
        GameThread gameThread = new GameThread(gameState);
        CharacterThread characterThread = new CharacterThread(gameState);
        EnemyThread enemyThread = new EnemyThread(gameState);
       
        Thread game = new Thread(gameThread);
        Thread character = new Thread(characterThread);
        Thread enemy = new Thread(enemyThread);

        game.start();
        character.start();
        enemy.start();

    }
}