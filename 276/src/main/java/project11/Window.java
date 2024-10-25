package project11;
import javax.swing.JFrame;
public class Window {
    private JFrame window;

    /**
     * Create a window for the game.
     */
    public Window() {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Group 11 ninja game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
