package project11;
import javax.swing.JFrame;

/**
 * Window class for creating a window
 */
public class Window {
    private JFrame window;

    /**
     * Creates Window
     * @param gamePanel GamePanel object
     */
    public Window(GamePanel gamePanel) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ninja Game");

        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
