package project11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * GamePanel for creating a game window, extends JPanel
 */
public class GamePanel extends JPanel {
    // Local variables, only need to change this to change game size
    private static final int TILE_SIZE = 32; // Set smaller TILE_SIZE for more cells
    private static final int PLAY_COLUMNS = 15; // Wider game area
    private static final int PLAY_ROWS = 10; // Taller game area
    private static final int BORDER_TILES = 1; // Border around play area
    private static final int DATA_TILES = 2;   // Data space above play area

    // Game state flags
    private boolean isGameStarted = false;

    // Getters
    public static int getTileSize() {
        return TILE_SIZE;
    }
    public static int getPlayColumns() {
        return PLAY_COLUMNS;
    }
    public static int getPlayRows() {
        return PLAY_ROWS;
    }
    public static int getBorderTiles() {
        return BORDER_TILES;
    }
    public static int getDataTiles() {
        return DATA_TILES;
    }

    protected GameState gameState;
    private Renderer renderer;

    /**
     * Create a GamePanel based on the current gamestate and keyhandler
     * @param gameState GameState object
     * @param keyHandler KeyHandler for keyboard input
     */
    public GamePanel(GameState gameState, KeyHandler keyHandler) {
        this.gameState = gameState;
        this.renderer = new Renderer(TILE_SIZE);
        // Calculate the panel dimensions based on TILE_SIZE and grid layout
        int width = (PLAY_COLUMNS + 2 * BORDER_TILES) * TILE_SIZE;
        int height = (PLAY_ROWS + 2 * BORDER_TILES + DATA_TILES) * TILE_SIZE;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

        // Add mouse listener for start screen interaction
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isGameStarted) {
                    int mouseX = e.getX();
                    int mouseY = e.getY();
                    // Check if the "Play" button was clicked
                    if (isPlayButtonClicked(mouseX, mouseY)) {
                        isGameStarted = true;
                        repaint();
                    }
                }
            }
        });
    }

    /**
     * Check if the "Play" button was clicked
     */
    private boolean isPlayButtonClicked(int mouseX, int mouseY) {
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int buttonY = (getHeight() - buttonHeight) / 2 + 50; // Centered + offset below header
        return mouseX >= buttonX && mouseX <= buttonX + buttonWidth &&
               mouseY >= buttonY && mouseY <= buttonY + buttonHeight;
    }

    /**
     * Repaint function, used by graphics for drawing every call
     */
    public void render() {
        repaint();
    }

    /**
     * Draw everything to window including stats or start screen
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isGameStarted) {
            drawStartScreen(g);
        } else {
            renderer.render(g, gameState.getGameObjects());
            g.setColor(Color.WHITE); // score
            g.drawString("Score: " + gameState.getScore(), 10, TILE_SIZE);
            g.setColor(Color.CYAN); // items
            g.drawString("Items: " + gameState.getCollectedItems() + "/" + gameState.getTotalItems() + "    Bonus Item: " + gameState.getBonusItem(), 10, TILE_SIZE * 2);
            g.setColor(Color.GREEN); // instructions
            g.drawString("Tip: Use WASD to move, collect all keys and reach the chest to win!", 10, TILE_SIZE * (PLAY_ROWS + 2 * BORDER_TILES + DATA_TILES));
        }
    }

    /**
     * Draw the initial start screen with a "Play" button
     */
    private void drawStartScreen(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        g.drawString("Ninja Game", getWidth() / 2 - 100, getHeight() / 2 - 40);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        int buttonWidth = 100;
        int buttonHeight = 40;
        int buttonX = (getWidth() - buttonWidth) / 2;
        int buttonY = (getHeight() - buttonHeight) / 2 + 50;
        g.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
        g.drawString("Play", buttonX + 25, buttonY + 28);
    }
}
